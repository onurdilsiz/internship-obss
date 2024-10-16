import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { req } from "../utils/client";
import SellerCard from "../components/seller/SellerCard";
import { useUser } from "../providers/UserContext";
import { SellerDetails } from "../components/seller/SellerCard";

export const SearchSellerPage = () => {
    const { query } = useParams();
    const { username } = useUser();

    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState("");
    const [sellers, setSellers] = useState<SellerDetails[]>([]);
    const [blacklistedSellers, setBlacklistedSellers] = useState<string[]>([]);

    const handleQuery = async () => {
        setIsLoading(true);
        setError("");
        setSellers([]);
        try {
            const searchQuery = `sellers?search=${query}`;
            const response = await req(searchQuery, "get", {});
            console.log("Search response:", response.data);
            const sellers: SellerDetails[] = response.data.payload.map(
                (seller: any, idx: number) => ({
                    id: idx,
                    seller_id: seller.id,
                    name: seller.name,
                    username: seller.username,
                    phone: seller.phone,
                    email: seller.email,
                    address: seller.address,
                    // Add any other properties you need
                })
            );
            if (sellers.length === 0) {
                throw new Error("No sellers found");
            }
            setSellers(sellers);
        } catch (error: any) {
            console.error("Search failed:", error);
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
            {/* {JSON.stringify(sellers, null, 2)} */}

            <div className="flex flex-col gap-4">
                {sellers.map((seller) => (
                    <SellerCard key={seller.id} seller={seller} refreshSellers={handleQuery} blacklistedSellers={blacklistedSellers}/>
                ))}
            </div>
        </div>
    );
};

export default SearchSellerPage;
