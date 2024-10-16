import React, { useState } from 'react';
import { SellerDetails } from './SellerCard';
import { req } from '../../utils/client';
import FormInput from '../FormInput';

function SellerEdit({ isOpen, setIsOpen, seller, refreshSellers }: { isOpen: boolean, setIsOpen: (isOpen: boolean) => void, seller: SellerDetails, refreshSellers: () => void }) {
    const [error, setError] = useState("");
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [phone, setPhone] = useState("");
    const [address, setAddress] = useState("");

    const handleEditSeller = async (e: React.FormEvent) => {
        e.preventDefault();
        setError("");

        try {
            let url = `sellers/` + seller.seller_id;
            // Update the request payload with the new state values
            await req(url, "put", {
                name: name ? name : seller.name,
                email: email ? email : seller.email,
                phone: phone ? phone : seller.phone,
                address: address ? address : seller.address
            });
            console.log("Seller updated successfully");
            refreshSellers(); // Call refreshFriends after successful update
        } catch (error: any) {
            setError(error.message);
        }

        // Close the popup after posting
        setIsOpen(false);
    };

    if (!isOpen) return null;
    return (
        <div className="seller-popup">
            {(
                <div className="card bg-base-100 shadow-xl">
                    <h2 className="card-title">Edit Seller</h2>
                    <FormInput type="text" placeholder={seller.name} value={name} onChange={(e: any) => setName(e.target.value)} />
                    <FormInput type="text" placeholder={seller.email} value={email} onChange={(e: any) => setEmail(e.target.value)} />
                    <FormInput type="text" placeholder={seller.phone} value={phone} onChange={(e: any) => setPhone(e.target.value)} />
                    <FormInput type="text" placeholder={seller.address} value={address} onChange={(e: any) => setAddress(e.target.value)} />
                    <div className="button-container">
                        <button className="btn btn-primary mt-4" onClick={handleEditSeller}>Save</button>
                        <button className="btn btn-primary mt-4" onClick={() => setIsOpen(false)}>Cancel</button>
                    </div>
                    <p className="text-red-500">{error}</p>
                </div>
            )}
        </div>
    );
}

export default SellerEdit;
