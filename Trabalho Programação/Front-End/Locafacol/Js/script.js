const goToCadastro = document.getElementById('goToCadastro');
const goToLogin = document.getElementById('goToLogin');
const container = document.getElementById('container');
const signUpContainer = document.querySelector('.sign-up-container');
const signInContainer = document.querySelector('.sign-in-container');

// Elementos do formulário de cadastro
const registerForm = document.getElementById('registerForm');
const registerName = document.getElementById('registerName');
const registerEmail = document.getElementById('registerEmail');
const registerPassword = document.getElementById('registerPassword');
const registerConfirmPassword = document.getElementById('registerConfirmPassword');
const registerAddress = document.getElementById('registerAddress');
const registerPhone = document.getElementById('registerPhone');

// Elementos do formulário de login
const loginForm = document.getElementById('loginForm');
const loginEmail = document.getElementById('loginEmail');
const loginPassword = document.getElementById('loginPassword');

// Alternar entre as telas
goToCadastro.addEventListener('click', () => {
    signUpContainer.style.transform = 'translateX(0)';
    signInContainer.style.transform = 'translateX(-100%)';
    signUpContainer.style.opacity = '1';
    signInContainer.style.opacity = '0';
});

goToLogin.addEventListener('click', () => {
    signUpContainer.style.transform = 'translateX(100%)';
    signInContainer.style.transform = 'translateX(0)';
    signUpContainer.style.opacity = '0';
    signInContainer.style.opacity = '1';
});

// Função para registrar os dados do usuário
registerForm.addEventListener('submit', (e) => {
    e.preventDefault();

    const name = registerName.value;
    const email = registerEmail.value;
    const password = registerPassword.value;
    const confirmPassword = registerConfirmPassword.value;
    const address = registerAddress.value;
    const phone = registerPhone.value;

    // Validação de senha
    if (password !== confirmPassword) {
        alert("As senhas não coincidem!");
        return;
    }

    // Salvar os dados no localStorage
    const userData = {
        name: name,
        email: email,
        password: password,
        address: address,
        phone: phone,
    };
    localStorage.setItem('user', JSON.stringify(userData));

    alert('Cadastro realizado com sucesso!');
    // Redirecionar para a tela de login
    goToLogin.click();
});

// Função para realizar o login
loginForm.addEventListener('submit', (e) => {
    e.preventDefault();

    const email = loginEmail.value;
    const password = loginPassword.value;

    // Recuperar os dados do localStorage
    const storedUser = JSON.parse(localStorage.getItem('user'));

    // Verificar se os dados estão corretos
    if (storedUser && storedUser.email === email && storedUser.password === password) {
        alert('Login realizado com sucesso!');
        // Redirecionar para a página home
        window.location.href = 'home.html'; // Coloque aqui o link correto da sua página "home"
    } else {
        alert('E-mail ou senha incorretos!');
    }
});