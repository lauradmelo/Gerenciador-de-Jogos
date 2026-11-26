const API = "/api/jogos";

let jogoEditando = null;


// LISTAR JOGOS
async function listarJogos() {
    const resposta = await fetch(API);
    const jogos = await resposta.json();

    const area = document.getElementById("jogos");

    area.innerHTML = "";

    if (jogos.length === 0) {
        area.innerHTML = "<p>Nenhum jogo cadastrado.</p>";
    }

    jogos.forEach(jogo => {

        let disponibilidade = "";

        if (jogo.disponivel === false) {
            disponibilidade = "<span class='indisponivel'>INDISPONÍVEL</span>";
        }

        area.innerHTML += `
            <div class="jogo">
                <div>
                    <h3>${jogo.titulo}</h3>
                    ${disponibilidade}
                    <p>Ano: ${jogo.ano}</p>
                    <p>Plataforma: ${jogo.plataforma}</p>
                    <p>Observações: ${jogo.observacoes || ""}</p>
                </div>

                <div>
                    <button onclick="editarJogo(${jogo.id})">Alterar</button>
                    <button onclick="excluirJogo(${jogo.id})">Excluir</button>
                </div>
            </div>
        `;
    });
}


// MOSTRAR FORMULÁRIO
function mostrarFormulario() {
    jogoEditando = null;

    document.getElementById("formJogo").reset();
    document.getElementById("tituloFormulario").innerText = "Incluir novo jogo";

    document.getElementById("lista").classList.add("escondido");
    document.getElementById("formulario").classList.remove("escondido");

    document.getElementById("mensagem").innerText = "";
}


// VOLTAR PARA A LISTA
function voltarLista() {
    document.getElementById("formulario").classList.add("escondido");
    document.getElementById("lista").classList.remove("escondido");

    listarJogos();
}


// CADASTRAR OU ALTERAR JOGO
document.getElementById("formJogo").addEventListener("submit", async function (event) {

    event.preventDefault();

    const jogo = {
        titulo: document.getElementById("titulo").value,
        ano: Number(document.getElementById("ano").value),
        plataforma: document.getElementById("plataforma").value,
        disponivel: document.querySelector('input[name="disponivel"]:checked').value === "true",
        observacoes: document.getElementById("observacoes").value
    };

    let resposta;

    if (jogoEditando === null) {

        resposta = await fetch(API, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(jogo)
        });

    } else {

        resposta = await fetch(API + "/" + jogoEditando, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(jogo)
        });
    }

    if (resposta.ok) {
        alert("Jogo salvo com sucesso!");
        voltarLista();

    } else {
        const erros = await resposta.json();

        let mensagemErro = "";

        for (let campo in erros) {
            mensagemErro += erros[campo] + "\n";
        }

        alert(mensagemErro);
    }
});


// EDITAR JOGO
async function editarJogo(id) {

    const resposta = await fetch(API + "/" + id);
    const jogo = await resposta.json();

    document.getElementById("titulo").value = jogo.titulo;
    document.getElementById("ano").value = jogo.ano;
    document.getElementById("plataforma").value = jogo.plataforma;
    document.getElementById("observacoes").value = jogo.observacoes;

    if (jogo.disponivel === true) {
        document.querySelector('input[name="disponivel"][value="true"]').checked = true;
    } else {
        document.querySelector('input[name="disponivel"][value="false"]').checked = true;
    }

    jogoEditando = id;

    document.getElementById("tituloFormulario").innerText = "Alterar jogo";
    document.getElementById("lista").classList.add("escondido");
    document.getElementById("formulario").classList.remove("escondido");
}


// EXCLUIR JOGO
async function excluirJogo(id) {

    const confirmar = confirm("Deseja realmente excluir este jogo?");

    if (confirmar === true) {

        const resposta = await fetch(API + "/" + id, {
            method: "DELETE"
        });

        if (resposta.ok) {
            alert("Jogo excluído com sucesso!");
            listarJogos();
        } else {
            alert("Não foi possível excluir o jogo.");
        }
    }
}


// CARREGAR A LISTA AO ABRIR A PÁGINA
listarJogos();
