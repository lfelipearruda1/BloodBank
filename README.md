# 🩸 BloodBank — Sistema de Gerenciamento de Bolsas de Sangue
---

## 🎯 Sobre o Sistema

O **BloodBank** é um sistema simples, robusto e funcional desenvolvido em **Java** com **MySQL**, voltado para o controle de **bolsas de sangue** e **estoque por tipo sanguíneo**.

O projeto é 100% funcional via **terminal (console)**, sem frameworks externos, ideal para aplicações didáticas, testes locais e ambientes com baixo consumo de recursos.

---

## 📌 Funcionalidades Implementadas

- ✅ Cadastro de novas bolsas de sangue
- ✅ Listagem de todas as bolsas cadastradas
- ✅ Busca de bolsa por ID
- ✅ Exclusão de bolsa
- ✅ Visualização do estoque total por tipo sanguíneo
- ✅ Atualização automática do estoque a cada inserção ou remoção

---

## 🧩 Entidades e Relacionamentos

### 🩸 Bolsa de Sangue
- ID (autoincrementado)
- Tipo sanguíneo (ex: A+, O−)

### 📦 Estoque
- ID (autoincrementado)
- Tipo sanguíneo
- Quantidade total de bolsas disponíveis
- Atualizado automaticamente

---

## 🔁 Fluxo de Operações

1. **Criação de Bolsa**  
   ➤ O usuário informa o tipo sanguíneo e o sistema registra no banco.

2. **Atualização do Estoque**  
   ➤ O DAO verifica se o tipo já existe no estoque:  
   - Se **sim**, incrementa a quantidade  
   - Se **não**, cria um novo tipo com quantidade 1

3. **Busca por ID**  
   ➤ O sistema retorna os dados da bolsa cadastrada.

4. **Exclusão de Bolsa**  
   ➤ O sistema remove a bolsa e atualiza o estoque.

5. **Listagem Geral**  
   ➤ O sistema exibe todas as bolsas de sangue cadastradas.

6. **Visualização de Estoque**  
   ➤ O sistema exibe o total de bolsas por tipo sanguíneo.

---

## 🛡️ Benefícios do Sistema

- **Rastreabilidade básica** das bolsas de sangue
- **Atualização automática do estoque**
- **Sistema leve e direto**, ideal para ambientes de aprendizado
- **Sem frameworks**, apenas Java + JDBC

---

## 🛠️ Tecnologias Utilizadas

- ☕ Java
- 🛢️ MySQL
- ⚙️ JDBC
- 🧱 SQL puro
- 🧰 Maven

---

## 🚀 Como Executar

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/BloodBank.git
cd BloodBank

# Compile o projeto (caso esteja usando Maven)
mvn clean compile

# Execute o Main.java via terminal ou IDE (ex: VSCode)


