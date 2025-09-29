# 🩸 BloodBank – Sistema de Gestão de Banco de Sangue  

## 📌 Visão Geral  
O **BloodBank** é um sistema desenvolvido para uma clínica especializada em **coleta, armazenamento e distribuição de sangue**, responsável por gerenciar todo o ciclo de doações de forma organizada, segura e eficiente.  

Ele permite acompanhar desde o **cadastro de doadores**, passando pelo **registro de doações** e **controle de estoque**, até a **distribuição de bolsas de sangue a pacientes em hospitais**.  

---

## 👥 Atores e Entidades Principais  

### 🧑‍🤝‍🧑 Doador  
- Pessoa cadastrada no sistema que realiza doações.  
- Cada doador possui **CPF, nome, idade, sexo, endereço e tipo sanguíneo fixo**.  
- Pode realizar várias doações, que geram **bolsas de sangue**.  

### 👨‍⚕️ Funcionário  
- Usuário responsável por operar o sistema.  
- Pode ser:  
  - **Administrador** → controla todas as funções, gestão de usuários e relatórios.  
  - **Atendente** → realiza registros de doadores, doações e solicitações de hospitais.  
- Funcionários estão vinculados a um hospital, mas podem ficar sem vínculo caso o hospital seja removido (**Delete Set Null**).  

### 🩸 Bolsa de Sangue  
- Unidade resultante de uma doação.  
- Possui **ID único, tipo sanguíneo e vínculo a um doador**.  
- Armazenada no estoque e distribuída a pacientes.  

### 🏥 Hospital  
- Instituição de saúde cadastrada no sistema.  
- Possui **CNPJ, nome, telefone e endereço**.  
- Pode ter **funcionários e gerentes associados**.  
- Faz solicitações de bolsas de sangue para atender pacientes.  

### 🧍 Paciente  
- Pessoa que recebe uma bolsa de sangue.  
- Possui **CPF, nome, idade e tipo sanguíneo**.  
- Está vinculada a um hospital e a uma bolsa específica.  

### 📦 Estoque  
- Controle da quantidade de bolsas disponíveis por tipo sanguíneo.  
- Atualizado automaticamente:  
  - Doações → estoque aumenta.  
  - Distribuição a pacientes → estoque diminui.  

### 📑 Solicitação  
- Pedido de bolsas de sangue feito por um hospital.  
- Contém: **tipo sanguíneo, quantidade, hospital solicitante, gerente e atendente responsável pelo registro**.  
- Cada solicitação gera a saída de bolsas do estoque.  

---

## 🔄 Fluxo das Operações  

1. **Cadastro de Doador**  
   - Atendente registra dados do doador.  

2. **Registro de Doação**  
   - Cada doação gera bolsas de sangue que são vinculadas ao doador.  

3. **Atualização de Estoque**  
   - O estoque é ajustado conforme entrada (doações) e saída (entrega a pacientes).  

4. **Solicitação de Bolsas**  
   - Hospital solicita quantidade e tipo sanguíneo.  
   - Pedido é registrado por um atendente.  

5. **Distribuição a Pacientes**  
   - Bolsas solicitadas são entregues a pacientes vinculados ao hospital.  

6. **Gestão de Funcionários**  
   - Administradores podem cadastrar/remover funcionários e gerar relatórios.  

---

## 🗂 Estrutura do Banco de Dados  
O banco foi implementado em **MySQL**, com as seguintes entidades:  
- **Hospital**  
- **Estoque**  
- **Doador**  
- **Paciente**  
- **Funcionario**  
- **Gerente**  
- **Endereco**  
- **Bolsa_Sangue**  
- **Solicitacao**  

### Exemplos de Regras de Negócio Implementadas  
- Idade mínima do doador: **18 anos**.  
- Sexo do doador limitado a **'M' ou 'F'**.  
- Estoque não pode ter valores negativos.  
- Solicitações de bolsas precisam de quantidade **> 0**.  

---

## 📎 Materiais Complementares  
Mais informações, diagramas e scripts relacionados ao projeto podem ser encontrados neste link:  
👉 [Google Drive – BloodBank](https://drive.google.com/drive/folders/1TAvxcFq4msZOg0LU04T1_vGHEx5dYgXq)  

---

📌 **Autores**:
- George Pessoa  
- Guilherme Alencar  
- Henrique Lobo  
- Luiz Felipe Arruda  
