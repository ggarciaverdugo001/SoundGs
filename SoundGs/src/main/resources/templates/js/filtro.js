// Obtener referencias a los elementos del formulario y el desplegable
const form = document.getElementById('filtroForm');
const generoSelect = document.getElementById('generoSelect');

// Escuchar el evento de cambio en el desplegable
generoSelect.addEventListener('change', () => {
  // Enviar el formulario automáticamente cuando se selecciona un género
  form.submit();
});
