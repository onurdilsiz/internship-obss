import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { req } from "../utils/client";
import ProductCard from "../components/ProductCard";

import { ProductDetails } from "../components/ProductCard";
import { useUser } from "../providers/UserContext";

export const SearchProductPage = () => {
  const { query } = useParams();
  const { username } = useUser();

  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState("");
  const [favList, setFavList] = useState<string[]>([]);
  const [products, setProducts] = useState<ProductDetails[]>([]);
  const handleQuery = async () => {
    setIsLoading(true);
    setError("");
    setProducts([]);
    try {
      const searchQuery = `products?search=${query}`;
      const response = await req(searchQuery, "get", {});
      console.log("Search response:", response.data);
      const products: ProductDetails[] = response.data.payload.map(
        (product: any, idx: number) => ({
          id: idx,
          product_id: product.id,
          name: product.name,
          title: product.title,   
          description: product.description,
          category: product.category,
          price: product.price,
          quantity: product.quantity,  
          seller_id: product.sellerId,
          seller_name: product.sellerName
                
        })
      );
      if (products.length === 0) {
          throw new Error("No products found");
      }
      setProducts(products);
    } catch (error: any) {
      console.error("Search failed:", error);
      setError(error.message);
    }
    setIsLoading(false);

    try{
        const favlistQuery = `users/`+username+`/favorite-products`;
        const response = await req(favlistQuery, "get", {});
        console.log("Sellers response:", response.data.payload);
        const favlist: string[] = response.data.payload;
        setFavList(favlist);

    }
    catch(error: any){
        console.error("Get failed:", error);
        setError(error.message);
    
    }




  };
  useEffect(() => {
    
    handleQuery();
  }, [query]);

  if (isLoading) {
    return (
      <div className="flex flex-col justify-center items-center pt-10">
        <span className="loading loading-spinner loading-lg"></span>
      </div>
    );
  }

  return (
    <div className="flex flex-col justify-center items-center pt-5">
      {/* <h1>Search Page {query}</h1> */}
      {error && <p className="text-red-500">{error}</p>}
      {/* {JSON.stringify(products, null, 2)} */}

      <div className="flex flex-col gap-4">
        {products.map((product) => (
          <ProductCard key={product.id} product={product} refreshProducts={ handleQuery} favList= {favList} />
        ))}
      </div>
    </div>
  );
};

export default SearchProductPage;