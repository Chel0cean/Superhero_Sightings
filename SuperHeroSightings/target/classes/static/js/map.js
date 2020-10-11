

        // Initialize and add the map
        function initMap(locationArray, openInfoWindow) {
            console.log("HELLO TEST");
            // The location of Uluru
            var firstLocation = { lat: locationArray[0].latitude, lng: locationArray[0].longitude };
            // The map, centered at Uluru
            var map = new google.maps.Map(
                document.getElementById('map'), { zoom: 4, center: firstLocation });
            // The marker, positioned at Uluru
            for (i = 0; i < locationArray.length; i++) {
                var location = locationArray[i];
                var position = { lat: location.latitude, lng: location.longitude };
                var title = location.locationName
                const marker = new google.maps.Marker({ position: position, map: map, title: title });
                var content =
                    '<div id="content">' + '<h1 id="firstHeading" class="firstHeading">' + location.locationName + '</h1>' +
                    '<div id="bodyContent">' +
                    '<p>' + location.locationDescription + '</p>' +
                    '<p>' + location.locationAddress + '</p>' +
                    '<p>' + location.locationCity + ', ' + location.locationState + ' ' + location.zipCode + ' ' + location.country + '</p>' +
                    '<p>' + location.latitude + ', ' + location.longitude + '</p>' +
                    "</div>" +
                    "</div>";

                var infowindow = new google.maps.InfoWindow()


                google.maps.event.addListener(marker, 'click', (function (marker, content, infowindow) {
                    return function () {

                        if (openInfoWindow)
                            openInfoWindow.close();

                        infowindow.setContent(content);
                        openInfoWindow = infowindow;
                        infowindow.open(map, marker);

                    };
                })(marker, content, infowindow));
            }

        }

        function closeOtherInfo() {
            if (InforObj.length > 0) {
                InforObj[0].set("marker", null);
                InforObj[0].close();
                InforObj.length = 0;
            }
        }

