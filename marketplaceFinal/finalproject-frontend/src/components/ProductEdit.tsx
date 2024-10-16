
import React, { useState } from 'react';
import FormInput from './FormInput';
import { req } from '../utils/client';
import { ProductDetails } from './ProductCard';


function ProductEdit({  isOpen, setIsOpen, product ,refreshProducts}: { isOpen: boolean, setIsOpen: (isOpen: boolean) => void ,  product:ProductDetails ,refreshProducts: () => void }) {
    const [error, setError] = useState("");
    const [name, setName] = useState("");
    const [category, setCategory] = useState("");
    const [description, setDescription] = useState("");
    const [price, setPrice] = useState(0);
    const [quantity, setQuantity] = useState(0);


    
    const handleEditProduct = async (e: React.FormEvent) => {
        e.preventDefault();
        setError("");

        
        try {
            let url = `products/`+product.product_id;
            await req(url, "put", {
                name: name ? name : product.name,
                category: category ? category : product.category,
                description: description ? description : product.description,
                price: price ? price : product.price,
                quantity: quantity ? quantity : product.quantity
            });
            console.log("Product updated successfully");
            refreshProducts();  // Call refreshFriends after successful update

          } catch (error: any) {
            setError(error.message);
          }
       
        
        // Close the popup after posting
        setIsOpen(false);
    };
    
  

    if(!isOpen) return null;
    return (
        <div className="product-popup">
           
            {(
                <div className="card bg-base-100 shadow-xl">
                    <h2 className="card-title">Edit Product</h2>
                    <FormInput  type="text" placeholder ={product.name} value ={name} onChange ={(e:any) => setName(e.target.value)} />
                    <FormInput  type="text" placeholder ={product.description} value ={description} onChange ={(e:any) => setDescription(e.target.value)} />
                    <FormInput  type="text" placeholder ={product.category} value ={category} onChange ={(e:any) => setCategory(e.target.value)} />
                    <FormInput  type="text" placeholder ={product.price} value ={price} onChange ={(e:any) => setPrice(e.target.value)} />
                    <FormInput  type="text" placeholder ={product.quantity} value ={quantity} onChange ={(e:any) => setQuantity(e.target.value)} />                    
                    <div className="button-container">
                        <button className="btn btn-primary mt-4" onClick={handleEditProduct}>Edit</button>
                        <button className="btn btn-primary mt-4" onClick={() => setIsOpen(false)}>Cancel</button>
                    </div>
                    <p className="text-red-500">{error}</p>
                </div>
            )}
        </div>
    );

}
export default ProductEdit;