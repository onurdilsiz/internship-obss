import React, { useEffect, useState } from 'react';
import FormInput from './FormInput';
import { ProductDetails } from './ProductCard';
import { req } from '../utils/client';

function ProductPopUp({  isOpen, setIsOpen }: { isOpen: boolean, setIsOpen: (isOpen: boolean) => void }) {

    const [error, setError] = useState("");

    const [product, setProduct] = useState<ProductDetails>({} as ProductDetails);
    const [sellers, setSellers] = useState<any[]>([]);


    const fetchSellers = async () => {
        try {
            const response = await req("sellers", "get", {});
            console.log("Sellers response:", response.data.payload);
            setSellers(response.data.payload);
        } catch (error: any) {
            console.error("Fetch sellers failed:", error);
        }
    };

    const handleAdd = async (e: React.FormEvent) => {
        e.preventDefault();
        setError("");
        try {
            console.log(product);
            const resp =await req("products", "post", {
                name: product.name,
                description: product.description,
                category: product.category,
                price: product.price,
                quantity: product.quantity,
                sellerId: product.seller_id

            });
            console.log("Product response:", resp.data);
            console.log('Product submitted');

          } catch (error: any) {
            setError(error.message);
          }
        // Add your logic to handle producting here
        // For example, you can send a request to your backend API
        // to save the product data
        
        // Close the popup after producting
        setIsOpen(false);
    };
    useEffect(() => {
        fetchSellers();
    }, []);

    if(!isOpen) return null;
    return (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50">
            <div className="card bg-base-100 shadow-xl p-5">
                <h1 className="text-2xl font-bold">Create a Product</h1>
                <FormInput type="text" placeholder="Product Name" value={product.name} onChange={(e: any) => setProduct({ ...product, name: e.target.value })} />
                <FormInput type="text" placeholder="Description" value={product.description} onChange={(e: any) => setProduct({ ...product, description: e.target.value })} />
                <FormInput type="text" placeholder="Category" value={product.category} onChange={(e: any) => setProduct({ ...product, category: e.target.value })} />
                <FormInput type="text" placeholder="Price" value={product.price} onChange={(e: any) => setProduct({ ...product, price: e.target.value })} />
                <FormInput type="text" placeholder="Quantity" value={product.quantity} onChange={(e: any) => setProduct({ ...product, quantity: e.target.value })} />
                <select className="select select-bordered w-full max-w-xs"
                    value={product.seller_id}
                    onChange={(e: any) => setProduct({ ...product, seller_id: e.target.value })}
                >
                    <option value="">Select a Seller</option>
                    {sellers.map((seller) => (
                        <option key={seller.id} value={seller.id}>
                            {seller.name}
                        </option>
                    ))}
                </select>
                <div className="button-container">
                    <button className="btn btn-primary mt-4" onClick={handleAdd}>Add a Product</button>
                    <button className="btn btn-danger mt-4" onClick={() => setIsOpen(false)}>Cancel</button>
                </div>
                <p className="text-red-500">{error}</p>
            </div>
        </div>
    );
}



export default ProductPopUp;