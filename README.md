# Cat Gallery Image Selector
This is a sample app which uses cat library as a gallery app for selecting cat image.

# In scope
1. API call to fetch the data.
2. Added custom action bar via material design toolbar which allows customization of drawable for back button, title, background color. 
   Additional custom actionbar is created which can be used instead of material design toolbar.
3. On tapping of any cat image, image path is shared to calling activity and image is loaded empty view present. 
4. Adapter uses data binding. 
5. Added pagination.
6. Given option switching between list and grid layout.
7. UI test case (which will run if internet is available as no dummy cached data is used). 
8. Generic error handling is done incase of network issue.

# Tools and devices
1. Android studio
2. Emulator Nexus 5X API 30
3. Emulator Pixel XL 4 API 30

# This project includes 
1. MVVM architecture
2. Retrofit
3. Coroutines
4. Google hilt
5. Data binding
6. Live data
7. Glide

# TODO
1. Add configuration structure. Also data caching can be added.
2. Pass base url from host app via configuration. 
3. We can use sharedViewModel for sharing data from cat library instead of activity.
4. Use security protocols. 
5. Minor bug fixes and ui changes can be scope for future changes.
