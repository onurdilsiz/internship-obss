import React , {useEffect, useState}from 'react';
import { useUser } from '../providers/UserContext';
import { req } from '../utils/client';
import ProductEdit from './ProductEdit';
import SvgIcon from './SvgIcon';
import { useNavigate } from 'react-router-dom';

export type ProductDetails = {
    id: string;
    product_id: string;
    name: string;
    description: string;
    category: string;
    price: string;
    quantity: string;
    seller_id: string;
    seller_name: string;
};

type ProductCardProps = {
    product: ProductDetails;
    refreshProducts: () => void;
    favList: string[];
};

const ProductCard: React.FC<ProductCardProps> = ({ product, refreshProducts, favList}) => {
    const { role} = useUser();
    const [isOpen, setIsOpen] = useState(false);
    const { username } = useUser();
    const [isFav, setisFav] = useState(false);
    const navigate = useNavigate();


    useEffect(() => {     
        const isfav = favList.includes(product.product_id);

        setisFav(isfav);
    }, [favList]);

    const handleAddToFavList = async () => {
        console.log(`Adding ${product.name} to fav list`);
        try{
            let url = `users/`+username+`/favorite-products/`+product.product_id;
            const response = await req(url, "post", {});
            console.log("Product added to fav list successfully");
            console.log(response.data);
            setisFav(true);
        }catch(error: any){
            console.error("Add to fav list failed:", error);
        }



    };

    
    const handleRemoveFromBlacklist = async () => {
        console.log(`Removing ${product.name} from fav list`);
        try{
            let url = `users/`+username+`/favorite-products/`+product.product_id;
            const response = await req(url, "delete", {});
            console.log("Product removed from fav list successfully");
            console.log(response.data);
            setisFav(false);

        }catch(error: any){
            console.error("Add to fav list failed:", error);
        }



    };


    const handleDelete = async (id:string) => {
        try{
        console.log(`Deleting ${id} to fav list`);
        let url = `products/`+id;
            await req(url, "delete", {});
            console.log("Product deleted successfully");
            refreshProducts();

        }catch (error: any) {
            console.error("Delete failed:", error);
        }
    };

    const handleNavigateToSeller = () => {
        navigate(`/sellers/${product.seller_id}`);
    };

    

    return (
        <div className="card bg-base-100 shadow-xl">
                    
                        <div className="card-body">
                        <figure>
                                <img
                                src={"https://upload.wikimedia.org/wikipedia/commons/1/14/No_Image_Available.jpg"}
                                alt= {product.name}/>
                            </figure>
                            <h2 className="card-title text-2xl font-bold text-gray-800 mb-2">
                                {product.name}
                            </h2>
                            <div className="flex flex-col gap-2 mb-4">
                                <div className="text-sm text-gray-600">
                                    <span className="font-semibold">Description: </span>
                                    {product.description}
                                </div>
                                <div className="text-sm text-gray-600">
                                    <div className="badge badge-secondary">  {product.category}</div>

                            
                                </div>
                                <div className="text-sm text-gray-600">
                                    <span className="font-semibold">Price: </span>
                                    ${product.price}
                                </div>
                                <div className="text-sm text-gray-600">
                                    <span className="font-semibold">Quantity: </span>
                                    {product.quantity}
                                </div>
                                <div className="text-sm text-gray-600">
                                <span className="font-semibold">Seller: </span>
                                <span 
                                    className="text-blue-500 hover:underline cursor-pointer" 
                                    onClick={handleNavigateToSeller}
                                >
                                    {product.seller_name}
                        </span>
                    </div>

                            </div>
                            {
                                isFav ? (
                                    <button className="btn btn-neutral" onClick={handleRemoveFromBlacklist}>
                                        Remove from Favorites
                                    </button>) : (
                                        <button className="btn btn-info" onClick={handleAddToFavList}>
                                            <SvgIcon icon= "like"/>Add to Favorites
                                        </button>)
                            }
                            <div className="card-actions justify-end">
                            
                                {role && (
                                    <>
                                        
                                        <button className="btn btn-secondary" onClick={() => setIsOpen(true) }>Edit </button>
                                        <button className="btn btn-danger" onClick={() =>{handleDelete(product.product_id)} }>Delete</button>
                        </>
                    )}
                    
                </div>
                {isOpen && <ProductEdit isOpen={isOpen} setIsOpen={setIsOpen} product={product} refreshProducts= {refreshProducts}/>}

            </div>
        </div>
    );
};

export default ProductCard;
