import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./DoadorList.css";

function DoadorList() {
  const [doadores, setDoadores] = useState([]);
  const [editandoCpf, setEditandoCpf] = useState(null);
  const [formEdicao, setFormEdicao] = useState({
    cpf: "",
    nome: "",
    idade: "",
    sexo: "",
    tipoSanguineo: "",
  });
  const navigate = useNavigate();

  useEffect(() => {
    carregarDoadores();
  }, []);

  const carregarDoadores = () => {
    fetch("http://localhost:8080/doador")
      .then((res) => res.json())
      .then((data) => setDoadores(data))
      .catch((err) => {
        console.error(err);
        alert("❌ Erro ao carregar doadores.");
      });
  };

  const handleDelete = async (cpf) => {
    if (!window.confirm("Tem certeza que deseja excluir este doador?")) return;

    try {
      const response = await fetch(`http://localhost:8080/doador?cpf=${cpf}`, {
        method: "DELETE",
      });

      if (response.ok) {
        alert("✅ Doador removido com sucesso!");
        carregarDoadores();
      } else {
        const data = await response.json();
        alert("❌ Erro ao remover doador: " + (data.erro || "Desconhecido"));
      }
    } catch (error) {
      console.error(error);
      alert("❌ Falha de conexão com o servidor!");
    }
  };

  const handleEditClick = (doador) => {
    setEditandoCpf(doador.cpf);
    setFormEdicao({ ...doador });
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormEdicao({ ...formEdicao, [name]: value });
  };

  const handleUpdate = async () => {
    try {
      const response = await fetch("http://localhost:8080/doador", {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formEdicao),
      });

      if (response.ok) {
        alert("✅ Doador atualizado com sucesso!");
        setEditandoCpf(null);
        carregarDoadores();
      } else {
        const data = await response.json();
        alert("❌ Erro ao atualizar doador: " + (data.erro || "Desconhecido"));
      }
    } catch (error) {
      console.error(error);
      alert("❌ Falha de conexão com o servidor!");
    }
  };

  const handleVoltar = () => {
    navigate("/"); // volta para Home
  };

  return (
    <div className="doador-list-container">
      <h2>📋 Lista de Doadores</h2>

      {doadores.length === 0 ? (
        <p>Nenhum doador cadastrado.</p>
      ) : (
        <table className="doador-table">
          <thead>
            <tr>
              <th>CPF</th>
              <th>Nome</th>
              <th>Idade</th>
              <th>Sexo</th>
              <th>Tipo Sanguíneo</th>
              <th>Ações</th>
            </tr>
          </thead>
          <tbody>
            {doadores.map((d) => (
              <tr key={d.cpf}>
                {editandoCpf === d.cpf ? (
                  <>
                    <td>{d.cpf}</td>
                    <td>
                      <input
                        type="text"
                        name="nome"
                        value={formEdicao.nome}
                        onChange={handleChange}
                      />
                    </td>
                    <td>
                      <input
                        type="number"
                        name="idade"
                        value={formEdicao.idade}
                        onChange={handleChange}
                      />
                    </td>
                    <td>
                      <select
                        name="sexo"
                        value={formEdicao.sexo}
                        onChange={handleChange}
                      >
                        <option value="M">Masculino</option>
                        <option value="F">Feminino</option>
                      </select>
                    </td>
                    <td>
                      <select
                        name="tipoSanguineo"
                        value={formEdicao.tipoSanguineo}
                        onChange={handleChange}
                      >
                        <option value="A+">A+</option>
                        <option value="A-">A-</option>
                        <option value="B+">B+</option>
                        <option value="B-">B-</option>
                        <option value="AB+">AB+</option>
                        <option value="AB-">AB-</option>
                        <option value="O+">O+</option>
                        <option value="O-">O-</option>
                      </select>
                    </td>
                    <td>
                      <button
                        className="save-button"
                        onClick={handleUpdate}
                      >
                        💾 Salvar
                      </button>
                      <button
                        className="cancel-button"
                        onClick={() => setEditandoCpf(null)}
                      >
                        ❌ Cancelar
                      </button>
                    </td>
                  </>
                ) : (
                  <>
                    <td>{d.cpf}</td>
                    <td>{d.nome}</td>
                    <td>{d.idade}</td>
                    <td>{d.sexo}</td>
                    <td>{d.tipoSanguineo}</td>
                    <td>
                      <button
                        className="edit-button"
                        onClick={() => handleEditClick(d)}
                      >
                        ✏️ Editar
                      </button>
                      <button
                        className="delete-button"
                        onClick={() => handleDelete(d.cpf)}
                      >
                        ❌ Excluir
                      </button>
                    </td>
                  </>
                )}
              </tr>
            ))}
          </tbody>
        </table>
      )}

      <button className="voltar-button" onClick={handleVoltar}>
        ⬅ Voltar
      </button>
    </div>
  );
}

export default DoadorList;
