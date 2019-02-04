<nav>
  <div class="nav-wrapper">
    <a href="<%= request.getContextPath() %>" class="brand-logo">Client Manager</a>
    <a href="#" data-target="client_manager" class="sidenav-trigger"><i class="material-icons">menu</i></a>
    <ul class="right hide-on-med-and-down">
      <li><a href="<%= request.getContextPath() %>"><i class="material-icons left lighten-2">list</i>Liste des clients</a></li>
      <li><a href="<%= request.getContextPath() %>/add"><i class="material-icons left lighten-2">add</i>Ajouter client</a></li>
    </ul>
  </div>
</nav>

<ul class="sidenav" id="client_manager">
      <li><a href="<%= request.getContextPath() %>"><i class="material-icons left lighten-2">list</i>Liste des clients</a></li>
      <li><a href="<%= request.getContextPath() %>/add"><i class="material-icons left lighten-2">add</i>Ajouter client</a></li>
</ul>