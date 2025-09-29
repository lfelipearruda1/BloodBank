import React from "react";
import { Link } from "react-router-dom";
import "./Home.css";

function Home() {
  return (
    <div className="home">
      {/* HERO */}
      <section className="hero">
        <div className="hero-text">
          <h1>
            Salve vidas<br />
            doando <span className="highlight">sangue</span>
          </h1>
          <p>Um sistema completo para gestão de doadores, hospitais e solicitações de sangue.</p>
          <Link to="/doadores/novo" state={{ from: "home" }} className="cta-button">
            Salve agora uma vida
          </Link>
        </div>
      </section>

      {/* ETAPAS */}
      <section className="section">
        <h2 className="section-title">Como doar sangue?</h2>
        <div className="steps">
          <div className="step">
            <div className="step-circle">1</div>
            <p>Preencha o cadastro com seus dados.</p>
          </div>
          <div className="step">
            <div className="step-circle">2</div>
            <p>Compareça ao local indicado.</p>
          </div>
          <div className="step">
            <div className="step-circle">3</div>
            <p>Doe com segurança e salve vidas!</p>
          </div>
        </div>
      </section>

      {/* CURIOSIDADES */}
      <section className="section">
        <h2 className="section-title">Curiosidades</h2>
        <div className="facts">
          <p>🩸 Uma doação pode salvar até 4 vidas.</p>
          <p>⏱️ A doação leva em média 30 minutos.</p>
          <p>❤️ Apenas 1,6% da população brasileira doa sangue.</p>
        </div>
      </section>

      {/* FAQ */}
      <section className="section">
        <h2 className="section-title">Perguntas Frequentes</h2>
        <div className="faq">
          <h3>Quem pode doar sangue?</h3>
          <p>Maiores de 16 anos com mais de 50kg e em boas condições de saúde.</p>

          <h3>Com que frequência posso doar?</h3>
          <p>Homens a cada 2 meses (máx. 4/ano), mulheres a cada 3 meses (máx. 3/ano).</p>
        </div>
      </section>

      {/* CTA FINAL */}
      <section className="final-cta section">
        <h2 className="section-title">Você pode fazer a diferença hoje</h2>
        <Link to="/doadores/novo" state={{ from: "home" }} className="cta-button">
          Torne-se um doador
        </Link>
      </section>
    </div>
  );
}

export default Home;
