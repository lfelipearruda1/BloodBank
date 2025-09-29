import React, { useState, useEffect } from "react";
import "./Hospital.css";

function Hospital() {
  const [hospitais, setHospitais] = useState([]);
  const [form, setForm] = useState({ cnpj: "", nome: "", endereco: "" });
  const [isEdit, setIsEdit] = useState(false);

  // Buscar hospitais
  useEffect(() => {
    fetch("http://localhost:8080/hospital")
      .then((res) => res.json())
      .then((data) => setHospitais(data))
      .catch((err) => console.error("Erro ao carregar hospitais:", err));
  }, []);

  // Atualizar formulário
  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  // Criar ou atualizar hospital
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const method = isEdit ? "PUT" : "POST";
      const response = await fetch("http://localhost:8080/hospital", {
        method,
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(form),
      });

      if (!response.ok) throw new Error("Erro ao salvar hospital");

      alert(isEdit ? "✅ Hospital atualizado!" : "✅ Hospital cadastrado!");
      setForm({ cnpj: "", nome: "", endereco: "" });
      setIsEdit(false);

      // Recarregar lista
      const updated = await fetch("http://localhost:8080/hospital").then((r) =>
        r.json()
      );
      setHospitais(updated);
    } catch (err) {
      alert("❌ " + err.message);
    }
  };

  // Editar hospital
  const handleEdit = (hospital) => {
    setForm(hospital);
    setIsEdit(true);
  };

  // Deletar hospital
  const handleDelete = async (cnpj) => {
    if (!window.confirm("Tem certeza que deseja excluir este hospital?")) return;
    try {
      const response = await fetch(
        `http://localhost:8080/hospital?cnpj=${cnpj}`,
        { method: "DELETE" }
      );

      if (!response.ok) throw new Error("Erro ao excluir hospital");

      alert("✅ Hospital removido!");
      setHospitais(hospitais.filter((h) => h.cnpj !== cnpj));
    } catch (err) {
      alert("❌ " + err.message);
    }
  };

  return (
    <div className="hospital-container">
      <h2>🏥 Gerenciar Hospitais</h2>

      {/* Formulário */}
      <form onSubmit={handleSubmit} className="hospital-form">
        <input
          type="text"
          name="cnpj"
          placeholder="CNPJ"
          value={form.cnpj}
          onChange={handleChange}
          required
          disabled={isEdit} // Não editar CNPJ
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
          type="text"
          name="endereco"
          placeholder="Endereço"
          value={form.endereco}
          onChange={handleChange}
          required
        />
        <button type="submit">{isEdit ? "Salvar Alterações" : "Cadastrar"}</button>
        {isEdit && (
          <button
            type="button"
            className="cancel-btn"
            onClick={() => {
              setForm({ cnpj: "", nome: "", endereco: "" });
              setIsEdit(false);
            }}
          >
            Cancelar
          </button>
        )}
      </form>

      {/* Lista */}
      <table className="hospital-table">
        <thead>
          <tr>
            <th>CNPJ</th>
            <th>Nome</th>
            <th>Endereço</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          {hospitais.map((h) => (
            <tr key={h.cnpj}>
              <td>{h.cnpj}</td>
              <td>{h.nome}</td>
              <td>{h.endereco}</td>
              <td>
                <button onClick={() => handleEdit(h)} className="edit-btn">
                  Editar
                </button>
                <button
                  onClick={() => handleDelete(h.cnpj)}
                  className="delete-btn"
                >
                  Excluir
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default Hospital;
