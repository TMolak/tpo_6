<!-- WEB-INF/searchResults.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Search Cars</title>
  <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css'>
  <script src='src/main/webapp/popup.js'></script>
</head>
<body>
<div class='container mt-5'>


  <h1 class='text-center'>Advanced Car Search</h1>
  <form action='search-servlet' method='get' class='mt-4'>
    <div class='form-row'>
      <div class='form-group col-md-6'>
        <label for='brand'>Brand:</label>
        <input type='text' class='form-control' id='brand' name='brand'>
      </div>
      <div class='form-group col-md-6'>
        <label for='model'>Model:</label>
        <input type='text' class='form-control' id='model' name='model'>
      </div>
    </div>
    <div class='form-row'>
      <div class='form-group col-md-6'>
        <label for='minHorsepower'>Min Horsepower:</label>
        <input type='number' class='form-control' id='minHorsepower' name='minHorsepower'>
      </div>
      <div class='form-group col-md-6'>
        <label for='minEngineSize'>Min Engine Size (L):</label>
        <input type='number' step='0.1' class='form-control' id='minEngineSize' name='minEngineSize'>
      </div>
    </div>
    <div class='form-row'>
      <div class='form-group col-md-6 ev-field'>
        <label for='transmission'>Transmission:</label>
        <select id='transmission' name='transmission' class='form-control'>
          <option value=''>Any</option>
          <option value='Manual'>Manual</option>
          <option value='Automatic'>Automatic</option>
        </select>
      </div>
      <div class='form-group col-md-6'>
        <div class='form-check mt-4'>
          <input type='checkbox' class='form-check-input' onclick='handleEvClick(event)'>
          <label class='form-check-label'>EV</label>
        </div>
      </div>
    </div>
    <button type='submit' class='btn btn-primary btn-block'>Search</button>
  </form>

  <div id='search-results' class='mt-5'>
    <c:choose>
      <c:when test="${empty carVersions}">
        <p class='text-center'>No results found.</p>
      </c:when>
      <c:otherwise>
        <table class='table table-bordered'>
          <thead class='thead-light'>
          <tr>
            <th>ID</th>
            <th>Brand</th>
            <th>Model</th>
            <th>Engine</th>
            <th>Horsepower</th>
            <th>Transmission</th>
            <th>Photo</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="carVersion" items="${carVersions}">
            <tr>
              <td>${carVersion.id}</td>
              <td>${carVersion.brandName}</td>
              <td>${carVersion.modelName}</td>
              <td>${carVersion.engine}</td>
              <td>${carVersion.horsepower}</td>
              <td>${carVersion.transmission}</td>
              <td>
                <button class='btn btn-link' onclick="handlePhotoClick('${carVersion.brandName}', '${carVersion.modelName}')">View Photo</button>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </c:otherwise>
    </c:choose>
  </div>
</div>
</body>
</html>

