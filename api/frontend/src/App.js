import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Layout from "./components/Layout";

// Páginas
import Home from "./pages/home/Home";
import DoadorForm from "./pages/doador/DoadorForm";
import DoadorList from "./pages/doador/DoadorList"; // ✅ import da lista
import Login from "./pages/login/Login";
import Estoque from "./pages/estoque/Estoque";
import Hospital from "./pages/hospital/Hospital";

function App() {
  return (
    <Router>
      <Layout>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/doadores/novo" element={<DoadorForm />} />
          <Route path="/doadores/lista" element={<DoadorList />} /> {/* ✅ nova rota */}
          <Route path="/login" element={<Login />} />
          <Route path="/estoque" element={<Estoque />} />
          <Route path="/hospitais" element={<Hospital />} />
        </Routes>
      </Layout>
    </Router>
  );
}

export default App;
