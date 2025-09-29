import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Login.css"; // importa o css que você mostrou

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();

    // Teste simples de login
    if (username.toLowerCase() === "guilherme" && password === "09891333469") {
      navigate("/sistema"); // redireciona para o sistema
    } else {
      alert("Usuário ou senha incorretos!");
    }
  };

  return (
    <section className="login-section">
      <div className="login-header">
        <h2>Login</h2>
      </div>

      <form className="login-form" onSubmit={handleSubmit}>
        <div className="form-row">
          <div className="form-group">
            <label htmlFor="username">Usuário</label>
            <input
              type="text"
              id="username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </div>
        </div>

        <div className="form-row">
          <div className="form-group">
            <label htmlFor="password">Senha</label>
            <input
              type="password"
              id="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>
        </div>

        <div className="form-footer">
          <button type="submit" className="submit-button">
            Entrar
          </button>
        </div>
      </form>
    </section>
  );
}

export default Login;
