document.addEventListener("DOMContentLoaded", function () {
    const cpfInput = document.getElementById("cpf");
    const telInput = document.getElementById("telefone");

    if (cpfInput) {
        cpfInput.addEventListener("input", function () {
            let value = cpfInput.value.replace(/\D/g, "");
            if (value.length > 11) value = value.slice(0, 11);
            cpfInput.value = value.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, "$1.$2.$3-$4");
        });
    }

    if (telInput) {
        telInput.addEventListener("input", function () {
            let value = telInput.value.replace(/\D/g, "");
            if (value.length > 11) value = value.slice(0, 11);
            telInput.value = value.replace(/(\d{2})(\d{5})(\d{4})/, "($1) $2-$3");
        });
    }
});
