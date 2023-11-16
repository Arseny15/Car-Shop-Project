# My Personal Project


# CarShop-Application
## An application that provides assistance in selecting a car to buy or to view (by car brand name, price, color, car year, km) for general users.


**Why is this project of interest to you?**

I will describe this idea from my perspective. 
I am a big fan of cars and often like to see different kinds of new and old cars that come on the market.
This activity raises my motivation to work harder and learn more. Choosing cars sets new goals for me.
Also, it's really convinient to find cars by using different settings.

**Who will use it?**

1. Car fans
2. Car sellers
3. Potential car buyers


**What will the application do?**

*For car sellers*:

- Add cars for sale (by car brand name, price, color, car year, km used)
- Remove their posts.


*For basic users*:

- Find a car by car brand
- Find a car by price
- Find a car by color
- Find a car by car year
- Find a car by km used
- Check all options avaliable



## User Stories
*Context*:
- As a user, I want to be able to search a car by car name.
- As a user, I want to be able to find car in terms of car brand name, color,car year, km used.
- As a user, I want to be able to view a list of all car choices.
- As a user, I want to be able to add it to the list of cars which I like.
- As a user(seller), I want to be able to add сars for sale to the app.
- As a user(seller), I want to be able to remove a posted сars for sale.

- As a user(seller), I want to be able to save my car list to file (if I so choose)
- As a user(seller), I want to be able to be able to load my car list from file (if I so choose)

## Instructions for grader
- You can generate the first required action related to the user story: Add/Remove cars by filling up the fields in 
seller section: car brand, car price, car color, car year, mileage.


- You can generate the second required action related to the user story: Use JCheckBox in potential car buyers section
for sorting cars by: car brand, same car price, under or equal specific price, color, car year, mileage.


- You can locate my visual component by pressing the button "SHOW IMAGE of DREAM CAR".


- You can save the state of my application by:
1. In Car Sellers panel, add a new car by filling up the fields(Car brand, Car price, Car color, Car year, Mileage).
2. Press button "Add car for sale"
3. Repeat steps 1,2 if you would like to add many cars
4. Press button "Save cars to the file"

- You can reload the state of my application by removing all cars from the file. 
- Two choices:
1. Press button "Load cars from the file" in Potential car buyers panel.
2. Fill up all fields and press "Remove car from sale list", until the list of cars is empty. 
3. Press "Save cars to the file", therefore, you will save empty list => reload the state.