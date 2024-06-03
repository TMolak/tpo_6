function openPopup(photoUrl) {
    var popup = window.open('', 'Car Photo', 'width=600,height=400');
    popup.document.write('<html><head><title>Car Photo</title></head><body>');
    popup.document.write('<img src="' + photoUrl + '" alt="Car Photo" style="width:100%;height:auto;" />');
    popup.document.write('</body></html>');
    popup.document.close();
}

async function fetchCarPhoto(make, model) {
    const apiKey = '50WF1eXOjR3qPyzw6SzF9a6kMr7tcPEGWe9LJV1o1LvuMOgaGwzfiXN8';
    const response = await fetch(`https://api.pexels.com/v1/search?query=${make}%20${model}&per_page=5`, {
        headers: {
            Authorization: apiKey
        }
    });
    const data = await response.json();
    if (data.photos.length > 0) {
        return data.photos[0].src.large;
    } else {
        return 'path/to/default/photo.jpg';
    }
}

async function handlePhotoClick(make, model) {
    const photoUrl = await fetchCarPhoto(make, model);
    openPopup(photoUrl);
}