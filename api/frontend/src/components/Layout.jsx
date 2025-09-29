import React, { useState, useEffect } from "react";
import { Link, useNavigate, useLocation } from "react-router-dom";
import "./Layout.css";
import { FaUserCircle } from "react-icons/fa";

function Layout({ children }) {
  const navigate = useNavigate();
  const location = useLocation();
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  useEffect(() => {
    const auth = sessionStorage.getItem("auth") === "true";
    setIsAuthenticated(auth);
  }, [location]);

  const handleProfileClick = () => {
    navigate("/sistema");
  };

  return (
    <>
      <header className="navbar">
        <div className="logo">
          <img src="/logo.png" alt="Logo" className="logo-img" />
          <span>Blood Bank</span>
        </div>

        <nav className="menu">
          <Link to="/">Início</Link>
          <Link to="/doadores/novo">Se Cadastre</Link>
          <Link to="/estoque">Estoque</Link>
          <Link to="/hospitais">Hospitais</Link>
          <Link to="/doadores/lista">Adm Cadastro</Link> {/* ✅ aqui leva pro DoadorList */}
          
          {isAuthenticated ? (
            <div className="avatar-wrapper">
              <div onClick={handleProfileClick} className="avatar-click">
                <FaUserCircle size={28} color="#7B1E1E" />
              </div>
            </div>
          ) : (
            <Link to="/login" className="login-button">
              Login
            </Link>
          )}
        </nav>
      </header>

      <main>{children}</main>
    </>
  );
}

export default Layout;
