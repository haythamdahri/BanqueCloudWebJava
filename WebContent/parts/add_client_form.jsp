<%@ page import="com.banque.entities.*" %>

<% Client client = (Client)request.getAttribute("client"); %>

<div class="row" style="margin-top: 20px;">
  <form class="col s12" method="POST">
      
      <div class="row">
        <div class="input-field col s12">
          <input id="code" type="text" name="code" value="#" readonly>
          <label for="code">Code</label>
        </div>
      </div>
      
      
      <div class="row">
        <div class="input-field col s12">
          <input id="nom" required aria-required="true" type="text" name="nom" class="validate">
          <label for="nom">Nom</label>
          <span class="helper-text" data-error="Remplir ce champ" data-success="OK">Nom</span>
        </div>
      </div>
      
      
      <div class="row">
        <div class="input-field col s12">
          <input id="prenom" required aria-required="true" type="text" name="prenom" class="validate">
          <label for="prenom">Prenom</label>
          <span class="helper-text" data-error="Remplir ce champ" data-success="OK">Prenom</span>
        </div>
      </div>
      
      
      <div class="row">
        <div class="input-field col s12">
          <input id="ville" required aria-required="true" type="text" name="ville" class="validate">
          <label for="ville">Ville</label>
          <span class="helper-text" data-error="Remplir ce champ" data-success="OK">Ville</span>
          
        </div>
      </div>
	<div class="row">
		<button class="btn waves-effect waves-light" type="submit" name="action">Ajouter
	    	<i class="material-icons right">send</i>
	  	</button>
	</div>
  </form>
</div>
      