import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";

function EstoqueForm() {
  const { id } = useParams();
  const isEdit = Boolean(id);
  const navigate = useNavigate();

  const [form, setForm] = useState({
    idEstoque: "",
    tipoSanguineo: "",
    qtdDisponivel: ""
  });

  useEffect(() => {
    if (isEdit) {
      fetch(`http://localhost:8080/estoque`)
        .then(res => res.json())
        .then(data => {
          const item = data.find(e => e.idEstoque === parseInt(id));
          if (!item) throw new Error("Estoque não encontrado");
          setForm({
            idEstoque: item.idEstoque,
            tipoSanguineo: item.tipoSanguineo,
            qtdDisponivel: item.qtdDisponivel.toString()
          });
        })
        .catch(err => {
          console.error(err);
          alert("Erro ao carregar dados do estoque.");
          navigate("/estoque");
        });
    }
  }, [isEdit, id, navigate]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm({ ...form, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const payload = {
      idEstoque: isEdit ? parseInt(form.idEstoque, 10) : undefined,
      tipoSanguineo: form.tipoSanguineo,
      qtdDisponivel: parseInt(form.qtdDisponivel, 10)
    };

    const url = "http://localhost:8080/estoque";
    const method = isEdit ? "PUT" : "POST";

    try {
      const response = await fetch(url, {
        method,
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload)
      });
      const data = await response.json();
      if (!response.ok) {
        throw new Error(data.erro || "Erro ao salvar estoque");
      }
      alert(data.mensagem || "Estoque salvo com sucesso!");
      navigate("/estoque");
    } catch (err) {
      alert("Erro ao salvar: " + err.message);
    }
  };

  const handleVoltar = () => {
    navigate("/sistema");
  };

  return (
    <div style={pageStyle}>
      <div style={headerStyle}>
        <h2>{isEdit ? "✏️ Editar Estoque" : "📦 Atualizar Estoque de Bolsas"}</h2>
      </div>

      <form onSubmit={handleSubmit} style={formStyle}>
        <div style={formRow}>
          <div style={formGroup}>
            <label>Tipo Sanguíneo:</label>
            <input
              type="text"
              name="tipoSanguineo"
              value={form.tipoSanguineo}
              onChange={handleChange}
              required
              disabled={isEdit}
              style={inputStyle}
            />
          </div>

          <div style={formGroup}>
            <label>Quantidade de Bolsas:</label>
            <input
              type="number"
              name="qtdDisponivel"
              value={form.qtdDisponivel}
              onChange={handleChange}
              required
              style={inputStyle}
            />
          </div>
        </div>

        <div style={buttonWrapper}>
          <button type="submit" style={submitButton}>
            {isEdit ? "Salvar Alterações" : "Salvar"}
          </button>
          <button
            type="button"
            onClick={handleVoltar}
            style={backButton}
            onMouseOver={e =>
              (e.currentTarget.style.backgroundColor = "#2c3e50")
            }
            onMouseOut={e =>
              (e.currentTarget.style.backgroundColor = "#34495e")
            }
          >
            Voltar
          </button>
        </div>
      </form>
    </div>
  );
}

const pageStyle = {
  maxWidth: "900px",
  margin: "40px auto",
  padding: "20px",
  backgroundColor: "#fff",
  borderRadius: "12px",
  boxShadow: "0 4px 12px rgba(0,0,0,0.08)",
  fontFamily: "'Segoe UI', sans-serif"
};

const headerStyle = { textAlign: "center", marginBottom: "30px" };
const formStyle = { padding: "20px" };
const formRow = {
  display: "flex",
  gap: "20px",
  marginBottom: "20px",
  flexWrap: "wrap"
};
const formGroup = { flex: "1", display: "flex", flexDirection: "column" };
const inputStyle = {
  padding: "10px",
  border: "1px solid #ccc",
  borderRadius: "6px",
  fontSize: "15px",
  marginTop: "6px"
};
const buttonWrapper = {
  display: "flex",
  justifyContent: "flex-end",
  gap: "10px",
  marginTop: "30px"
};
const submitButton = {
  padding: "12px 30px",
  backgroundColor: "#7B1E1E",
  color: "white",
  border: "none",
  borderRadius: "8px",
  fontWeight: "bold",
  fontSize: "16px",
  cursor: "pointer",
  transition: "background-color 0.3s ease"
};
const backButton = {
  padding: "12px 30px",
  backgroundColor: "#34495e",
  color: "white",
  border: "none",
  borderRadius: "8px",
  fontWeight: "bold",
  fontSize: "16px",
  cursor: "pointer",
  transition: "background-color 0.3s ease"
};

export default EstoqueForm;
