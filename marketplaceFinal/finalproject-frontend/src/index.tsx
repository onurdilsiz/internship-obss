import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import reportWebVitals from './reportWebVitals';
import Layout from './components/Layout';
import { Routes, Route, BrowserRouter } from 'react-router-dom';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import HomePage from './pages/HomePage';
import { UserProvider } from './providers/UserContext';
import SearchProductPage from './pages/SearchProductPage';
import UsersPage from './pages/UsersPage';
import SellersPage from './pages/SellersPage';
import SearchUserPage from './pages/SearchUserPage';
import SearchSellerPage from './pages/SearchSellerPage';
import SellerDetailPage from './pages/SellerDetailPage';
import FavProductsPage from './pages/FavProductsPage';
import BlacklistedSellerPage from './pages/BlacklistedSellerPage';

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
root.render(
  <React.StrictMode>
    <BrowserRouter>
    <UserProvider>

      <Layout>
        <Routes>
            <Route path="/login" element={<LoginPage />} />
            <Route path="/register" element={<RegisterPage />} />
            <Route path="/" element={<HomePage />} />
            <Route path="/users" element={<UsersPage />} />
            <Route path="/search/:query" element={<SearchProductPage />} />
            <Route path="/search-user/:query" element={<SearchUserPage />} />
            <Route path="/search-seller/:query" element={<SearchSellerPage />} />


            <Route path="/sellers" element={<SellersPage />} />
            
            <Route path="/sellers/:query" element={<SellerDetailPage />} />
            <Route path="/favproducts" element={<FavProductsPage />} />
            <Route path="/blacklist" element={<BlacklistedSellerPage />} />

            

            

        </Routes>



      </Layout>
    </UserProvider>
    </BrowserRouter>

   
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
