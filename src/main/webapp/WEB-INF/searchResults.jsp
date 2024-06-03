<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Wyszukaj Autka</title>
  <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css'>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
  <style>
    body {
      background-image: url('../background.jpg');
      background-size: cover;
      background-attachment: fixed;
      background-repeat: no-repeat;
    }
    .container {
      background-color: rgba(255, 255, 255, 0.8);
      padding: 20px;
      border-radius: 8px;
    }
  </style>
  <script src='popup.js'></script>
  <script>
    let sortDirection = {};
    function sortTable(columnIndex, isNumeric) {
      const table = document.getElementById("resultsTable");
      const tbody = table.tBodies[0];
      const rows = Array.from(tbody.rows);
      const direction = sortDirection[columnIndex] === 'asc' ? 'desc' : 'asc';
      sortDirection[columnIndex] = direction;

      rows.sort((a, b) => {
        const x = a.cells[columnIndex].innerText;
        const y = b.cells[columnIndex].innerText;

        if (isNumeric) {
          return direction === 'asc' ? x - y : y - x;
        } else {
          return direction === 'asc' ? x.localeCompare(y) : y.localeCompare(x);
        }
      });

      rows.forEach(row => tbody.appendChild(row));
    }

    let clickCount = 0;

    function handleEvClick(event) {
      event.preventDefault();
      clickCount++;
      if (clickCount === 3) {
        event.target.parentElement.style.display = 'none';
        return;
      }
      const randomTop = Math.floor(Math.random() * (window.innerHeight - 50)) + 'px';
      const randomLeft = Math.floor(Math.random() * (window.innerWidth - 50)) + 'px';
      event.target.parentElement.style.top = randomTop;
      event.target.parentElement.style.left = randomLeft;
    }
  </script>
</head>
<body>
<div class='container mt-5'>
  <h1 class='text-center'>Wyszukaj Autka</h1>
  <form action='search-servlet' method='get' class='mt-4'>
    <div class='form-row'>
      <div class='form-group col-md-6'>
        <label for='brand'>Marka:</label>
        <input type='text' class='form-control' id='brand' name='brand'>
      </div>
      <div class='form-group col-md-6'>
        <label for='model'>Model:</label>
        <input type='text' class='form-control' id='model' name='model'>
      </div>
    </div>
    <div class='form-row'>
      <div class='form-group col-md-6'>
        <label for='minHorsepower'>Min ilość KM:</label>
        <input type='number' class='form-control' id='minHorsepower' name='minHorsepower'>
      </div>
      <div class='form-group col-md-6'>
        <label for='minEngineSize'>Min rozmiar silnika (L):</label>
        <input type='number' step='0.1' class='form-control' id='minEngineSize' name='minEngineSize'>
      </div>
    </div>
    <div class='form-row'>
      <div class='form-group col-md-6 ev-field'>
        <label for='transmission'>Skrzynia biegów:</label>
        <select id='transmission' name='transmission' class='form-control'>
          <option value=''>Jakakolwiek</option>
          <option value='Manual'>Normalna</option>
          <option value='Automatic'>Nienormalna</option>
        </select>
      </div>
      <div class='form-group col-md-6'>
        <div class='form-check mt-4'>
          <input type='checkbox' class='form-check-input' onclick='handleEvClick(event)'>
          <label class='form-check-label'>Elektryk?</label>
        </div>
      </div>
    </div>
    <button type='submit' class='btn btn-primary btn-block'>Szukaj</button>
  </form>

  <div id='search-results' class='mt-5'>
    <c:choose>
      <c:when test="${empty carVersions}">
        <p class='text-center'>Ni ma.</p>
      </c:when>
      <c:otherwise>
        <table id='resultsTable' class='table table-bordered'>
          <thead class='thead-light'>
          <tr>
            <th onclick="sortTable(0, true)">ID <i class="fas fa-sort" ></i></th>
            <th onclick="sortTable(1, false)">Marka <i class="fas fa-sort" ></i></th>
            <th onclick="sortTable(2, false)">Model <i class="fas fa-sort" ></i></th>
            <th onclick="sortTable(3, false)">Silnik <i class="fas fa-sort" ></i></th>
            <th onclick="sortTable(4, true)">KM <i class="fas fa-sort" ></i></th>
            <th onclick="sortTable(5, false)">Skrzynia biegów <i class="fas fa-sort" ></i></th>
            <th>Zdjęcia</th>
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
                <button class='btn btn-link' onclick="handlePhotoClick('${carVersion.brandName}', '${carVersion.modelName}')">Pokaż Autko</button>
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
