import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { SellerDetails } from '../components/seller/SellerCard';
import { ProductDetails } from '../components/ProductCard';
import { req } from '../utils/client';
import SellerCard from '../components/seller/SellerCard';
import ProductCard from '../components/ProductCard';
import { useUser } from '../providers/UserContext';






const SellerDetailPage = ( ) => {
    const { query } = useParams();



    const [seller, setSeller] = useState<SellerDetails | null>(null);
    const [products, setProducts] = useState<ProductDetails[]>([]);
    const [error, setError] = useState("");
    const [favList, setFavList] = useState<string[]>([]);
    const { username } = useUser();
    const [isBlacklisted, setIsBlacklisted] = useState(false);
    const [blacklistedSellers, setBlacklistedSellers] = useState<string[]>([]);


    const fetchSeller = async () => {
        try {
            const response = await req(`sellers/${query}`, "get", {});
            setSeller(response.data.payload);
            const products: ProductDetails[] = response.data.payload.products.map(
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
                  seller_name: product.sellerName,
                })
              );
              console.log("Products:", products);
              setProducts(products);
        } catch (error) {
            console.error("Fetch seller failed:", error);
        }
        try{
            const favlistQuery = `users/`+username+`/favorite-products`;
            const response = await req(favlistQuery, "get", {});
            console.log("FavList response:", response.data.payload);
            const favlist: string[] = response.data.payload;
            setFavList(favlist);
    
        }
        catch(error: any){
            console.error("Get failed:", error);
            setError(error.message);
        
        }
        try{
            const blacklistQuery = `users/`+username+`/blacklist-seller`;
            const response = await req(blacklistQuery, "get", {});
            console.log("Sellers response:", response.data.payload);
            const blacklist: string[] = response.data.payload;
            setBlacklistedSellers(blacklist);
            if(blacklist.includes(query!)){
                setIsBlacklisted(true);
            }

        }
        catch(error: any){
            console.error("Get failed:", error);
            setError(error.message);
        
        }

    };
   
    const handleAddToBlacklist = async () => {
        try{
            let url = `users/`+username+`/blacklist-seller/`+seller!.id;
            const response = await req(url, "put", {});
            console.log("Seller added to blacklist successfully");
            console.log(response.data);
            setIsBlacklisted(true);
        }catch(error: any){
            console.error("Add to  blacklist failed:", error);
        }
    };

    const handleRemoveFromBlacklist = async () => {
        try{
            let url = `users/`+username+`/blacklist-seller/`+seller!.id;
            const response = await req(url, "delete", {});
            console.log("Seller remove from blacklist successfully");
            console.log(response.data);
            setIsBlacklisted(false);

        }catch(error: any){
            console.error("Remove from blacklist failed:", error);
        }



    };

    useEffect(() => {
 
        fetchSeller();


    }, []);
 
  
    if (!seller) {
        return <div>Loading...</div>;
    }

    return (
        <div className="p-4">
            <h1 className="text-2xl font-bold mb-4">{seller.name}</h1>
            <p className="mb-2">Phone: {seller.phone}</p>
            <p className="mb-2">Email: {seller.email}</p>
            <p className="mb-2">Address: {seller.address}</p>
            { !isBlacklisted ? (
                    <button className="btn btn-neutral" onClick={handleAddToBlacklist}>
                        Add to Blacklist
                    </button>
                ):(
                    <button className="btn btn-warning" onClick={handleRemoveFromBlacklist}>
                        Remove from Blacklist
                    </button>
                )}

            <h1 className="text-2xl font-bold mt-8 mb-4">Products</h1>
            {products.length === 0 ? (
                <p>No products available</p>
            ) : (
                <ul className="space-y-4">
                    {products.map((product) => (
                        <li key={product.id}>
                            <ProductCard product={product} refreshProducts={fetchSeller} favList={favList} />
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
};

export default SellerDetailPage;