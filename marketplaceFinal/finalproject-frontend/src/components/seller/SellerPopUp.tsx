import React, { useState } from 'react';
import FormInput from './../FormInput';
import { req } from '../../utils/client';
import { SellerDetails } from './SellerCard';


function SellerPopUp({ isOpen, setIsOpen }: { isOpen: boolean, setIsOpen: (isOpen: boolean) => void }) {

    const [error, setError] = useState("");

    const [seller, setSeller] = useState<SellerDetails>({} as SellerDetails);

    const handleAdd = async (e: React.FormEvent) => {
        e.preventDefault();
        setError("");
        try {
            console.log(seller);
            const resp = await req("sellers", "post", {
                name: seller.name,
                phone: seller.phone,
                username: seller.username,
                email: seller.email,
                address: seller.address,
                // Add other seller properties here
            });
            console.log("Seller response:", resp.data);
            console.log('Seller submitted');

        } catch (error: any) {
            setError(error.message);
        }

        setIsOpen(false);
    };

    if (!isOpen) return null;
    return (
        <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50">
            <div className="card bg-base-100 shadow-xl p-5">
                <h1 className="text-2xl font-bold">Create a Seller</h1>
                <FormInput type="text" placeholder="Name" value={seller.name} onChange={(e: any) => setSeller({ ...seller, name: e.target.value })} />
                <FormInput type="text" placeholder="Phone" value={seller.phone} onChange={(e: any) => setSeller({ ...seller, phone: e.target.value })} />
                <FormInput type="email" placeholder="Email" value={seller.email} onChange={(e: any) => setSeller({ ...seller, email: e.target.value })} />
                <FormInput type="text" placeholder="Address" value={seller.address} onChange={(e: any) => setSeller({ ...seller, address: e.target.value })} />

                {/* Add other seller input fields here */}
                <div className="button-container">
                    <button className="btn btn-primary mt-4" onClick={handleAdd}>Add a Seller</button>
                    <button className="btn btn-primary mt-4" onClick={() => setIsOpen(false)}>Cancel</button>
                </div>
                <p className="text-red-500">{error}</p>
            </div>
        </div>
    );
}

export default SellerPopUp;
