# Overview
The intention of this workshop is to learn about background processing in Android, and how to request data from external APIs. 

- Using AsyncTask for background work in Activities.
- Retrieve network data with OkHttp.
- Request data to a REST API using Retrofit.
- Store local data in SharedPreferences.

In this workshop we're not building a new project from scratch, but rather using a base project with a couple of activities and evolve it integrating the features above.

## The base project
We're building an application that lists Super Heroes retrieved from the Marvel API. Our base project has two Activities: 

- Super Heroes list (MainActivity): A list of images and names of the available super heroes.
- Super Hero Detail (SuperHeroDetailActivity): A detailed view of a single Super Hero, with bigger image, name and description.

The Super Heroes are retrieved from each Activity through a SuperHeroesRepository. In the base project this repository is implemented with fake data and a Thread.sleep() to simulate network delay.

MainActivity                        | SuperHeroDetailActivity
:----------------------------------:|:------------------------------:
![](art/MainActivityScreenshot.png) | ![](art/SuperHeroDetailActivityScreenshot.png)


# Companion slides
TODO

# Step by Step
TODO

# Contents
TODO