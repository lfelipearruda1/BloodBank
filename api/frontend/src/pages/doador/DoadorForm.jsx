import React, { useState } from "react";
import "./DoadorForm.css";

export default function DoadorForm() {
  const [form, setForm] = useState({
    cpf: "",
    nome: "",
    idade: "",
    sexo: "",
    tipoSanguineo: ""
  });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch("http://localhost:8080/doador", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(form)
      });

      if (response.ok) {
        alert("✅ Doador cadastrado com sucesso!");
        setForm({ cpf: "", nome: "", idade: "", sexo: "", tipoSanguineo: "" });
      } else {
        alert("❌ Erro ao cadastrar doador!");
      }
    } catch (error) {
      console.error("Erro:", error);
      alert("❌ Falha de conexão com o servidor!");
    }
  };

  return (
    <div className="doador-form-container">
      <h2>Cadastro de Doador</h2>
      <form onSubmit={handleSubmit} className="doador-form">
        <input
          type="text"
          name="cpf"
          placeholder="CPF"
          value={form.cpf}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="nome"
          placeholder="Nome"
          value={form.nome}
          onChange={handleChange}
          required
        />
        <input
          type="number"
          name="idade"
          placeholder="Idade"
          value={form.idade}
          onChange={handleChange}
          required
        />
        <select name="sexo" value={form.sexo} onChange={handleChange} required>
          <option value="">Sexo</option>
          <option value="M">Masculino</option>
          <option value="F">Feminino</option>
        </select>
        <select
          name="tipoSanguineo"
          value={form.tipoSanguineo}
          onChange={handleChange}
          required
        >
          <option value="">Tipo sanguíneo</option>
          <option value="A+">A+</option>
          <option value="A-">A-</option>
          <option value="B+">B+</option>
          <option value="B-">B-</option>
          <option value="AB+">AB+</option>
          <option value="AB-">AB-</option>
          <option value="O+">O+</option>
          <option value="O-">O-</option>
        </select>

        <button type="submit">Cadastrar</button>
      </form>
    </div>
  );
}
