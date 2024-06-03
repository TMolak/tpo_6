async function fetchCarPhotos(make, model) {
    const apiKey = '50WF1eXOjR3qPyzw6SzF9a6kMr7tcPEGWe9LJV1o1LvuMOgaGwzfiXN8';
    const response = await fetch(`https://api.pexels.com/v1/search?query=${make}%20${model}&per_page=5`, {
        headers: {
            Authorization: apiKey
        }
    });

    if (!response.ok) {
        console.error('Error fetching the photos:', response.statusText);
        return ['path/to/default/photo.jpg'];
    }

    const data = await response.json();
    console.log('Pexels API Response:', data);

    if (data.photos && data.photos.length > 0) {
        return data.photos.map(photo => photo.src.large);
    } else {
        return ['path/to/default/photo.jpg'];
    }
}

function openPopup(photoUrls) {
    var popup = window.open('', 'Car Photos', 'width=800,height=600');
    popup.document.write('<html><head><title>Car Photos</title></head><body>');
    photoUrls.forEach(photoUrl => {
        popup.document.write('<img src="' + photoUrl + '" alt="Car Photo" style="width:100%;height:auto;margin-bottom:10px;" />');
    });
    popup.document.write('</body></html>');
    popup.document.close();
}

async function handlePhotoClick(make, model) {
    try {
        const photoUrls = await fetchCarPhotos(make, model);
        openPopup(photoUrls);
    } catch (error) {
        console.error('Error handling photo click:', error);
        openPopup(['path/to/default/photo.jpg']); // Fallback image
    }
}
