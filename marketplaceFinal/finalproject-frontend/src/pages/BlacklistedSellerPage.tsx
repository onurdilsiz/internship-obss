import React, { useState, useEffect } from 'react';
import { req } from '../utils/client';
import SellerCard, { SellerDetails } from '../components/seller/SellerCard';
import { useUser } from '../providers/UserContext';

const BlacklistedSellerPage = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState("");
    const [sellers, setSellers] = useState<SellerDetails[]>([]);
    const [showPopup, setShowPopup] = useState(false);
    const { role, username } = useUser();
    const [blacklistedSellers, setBlacklistedSellers] = useState<string[]>([]);


    const refreshSellers = async () => {
        setIsLoading(true);
        setError("");
        setSellers([]);
        try {
            const blacklistQuery = `users/`+username+`/blacklist-seller-details`;
            const response = await req(blacklistQuery, "get", {});
            console.log("Sellers response:", response.data.payload);
            const sellers: SellerDetails[] = response.data.payload.map(
                (seller: any, idx: number) => ({
                    id: idx,
                    seller_id: seller.id,
                    name: seller.name,
                    surname: seller.surname,
                    email: seller.email,
                    phone: seller.phone,
                    address: seller.address,
                })
            );
            if (sellers.length === 0) {
                throw new Error("No sellers found");
            }
            setSellers(sellers);
        } catch (error: any) {
            console.error("Get failed:", error);
            setError(error.message);
        }
        try{
            const blacklistQuery = `users/`+username+`/blacklist-seller`;
            const response = await req(blacklistQuery, "get", {});
            console.log("Sellers response:", response.data.payload);
            const blacklist: string[] = response.data.payload;
            setBlacklistedSellers(blacklist);

        }
        catch(error: any){
            console.error("Get failed:", error);
            setError(error.message);
        
        }



        setIsLoading(false);
    };

    useEffect(() => {
        refreshSellers();
    }, []);

    if (isLoading) {
        return (
            <div className="flex flex-col justify-center items-center pt-10">
                <span className="loading loading-spinner loading-lg"></span>
            </div>
        );
    }

    return (
        <div className="flex flex-col justify-center items-center pt-5">
        <h1 className="text-3xl font-bold mb-4">Blacklisted Sellers</h1>

            {error && <p className="text-red-500">{error}</p>}
            <div className="flex flex-col gap-4">
                {sellers.map((seller) => (
                    <SellerCard key={seller.id} seller={seller} refreshSellers={refreshSellers}  blacklistedSellers ={blacklistedSellers}/>
                ))}
            </div>
        
        </div>
    );
};

export default BlacklistedSellerPage;
