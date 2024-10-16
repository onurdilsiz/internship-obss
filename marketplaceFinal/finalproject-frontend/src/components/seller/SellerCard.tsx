import React, { useEffect, useState } from 'react';
import { useUser } from '../../providers/UserContext';
import { req } from '../../utils/client';
import SellerEdit from './SellerEdit'; 
import { useNavigate } from 'react-router-dom';
import { error } from 'console';
export type SellerDetails = {
    id: string;
    seller_id: string;
    name: string;
    email: string;
    phone: string;
    username: string;
    address: string;
};

type SellerCardProps = {
    seller: SellerDetails;
    refreshSellers: () => void;
    blacklistedSellers: string[];
};

const SellerCard: React.FC<SellerCardProps> = ({ seller, refreshSellers, blacklistedSellers}) => {
    const { role } = useUser();
    const [isOpen, setIsOpen] = useState(false);
    const { username } = useUser();
    const [isBlacklisted, setIsBlacklisted] = useState(false);
    const [errorVisible, setErrorVisible] = useState("");
    const navigate = useNavigate();

    useEffect(() => {
        const isblacklisted = blacklistedSellers.includes(seller.seller_id);
        setIsBlacklisted(isblacklisted);
    }, [blacklistedSellers]);

    
    const handleAddToBlacklist = async () => {
        try{
            let url = `users/`+username+`/blacklist-seller/`+seller.seller_id;
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
            let url = `users/`+username+`/blacklist-seller/`+seller.seller_id;
            const response = await req(url, "delete", {});
            console.log("Seller remove from blacklist successfully");
            console.log(response.data);
            setIsBlacklisted(false);

        }catch(error: any){
            console.error("Remove from blacklist failed:", error);
        }



    };




    const handleDelete = async (id: string) => {
        try {
            console.log(`Deleting seller with ID: ${id}`);
            let url = `sellers/${id}`;
            await req(url, "delete", {});
            console.log("Seller deleted successfully");

            // Refresh seller list
            refreshSellers();
        } catch (error: any) {
            console.error("Delete failed:", error);
            setErrorVisible("You cannot delete a seller that has products favorited by users");
        }
    };
    
    const handleNavigateToSeller = () => {
        navigate(`/sellers/${seller.seller_id}`);
    };


    return (
        <div className="card bg-base-100 shadow-xl">
            <div className="card-body">
                <h2 className="card-title text-2xl font-bold text-gray-800 mb-2">
                <span 
                                    className="text-blue-500 hover:underline cursor-pointer" 
                                    onClick={handleNavigateToSeller}
                                >
                                    {seller.name}
                        </span>
                </h2>
                <div className="flex flex-col gap-2 mb-4">
                    <div className="text-sm text-gray-600">
                        <span className="font-semibold">Email: </span>
                        {seller.email}
                    </div>
                    <div className="text-sm text-gray-600">
                        <span className="font-semibold">Phone: </span>
                        {seller.phone}
                    </div>
                    <div className="text-sm text-gray-600">
                        <span className="font-semibold">Address: </span>
                        {seller.address}
                    </div>
                </div>
                { !isBlacklisted ? (
                    <button className="btn btn-neutral" onClick={handleAddToBlacklist}>
                        Add to Blacklist
                    </button>
                ):(
                    <button className="btn btn-warning" onClick={handleRemoveFromBlacklist}>
                        Remove from Blacklist
                    </button>
                )}
                  
                <div className="card-actions justify-end">
                    {role && (
                        <>
                            <button className="btn btn-secondary" onClick={() => setIsOpen(true)}>
                                Edit
                            </button>
                            <button className="btn btn-danger" onClick={() => handleDelete(seller.seller_id)}>
                                Delete
                            </button>
                        </>
                    )}
                </div>
                {errorVisible && <div className="text-red-500">{errorVisible}</div>}
                {isOpen && <SellerEdit isOpen={isOpen} setIsOpen={setIsOpen} seller={seller} refreshSellers={refreshSellers} />}
            </div>
        </div>
    );
};

export default SellerCard;
