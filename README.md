# Assignment 4: API Adventures 

[source](https://github.com/rael346/cs-4520-assignment-4)

## Project Structure
- `adapters`: contains the adapters for RecyclerView
- `api`: contains the services for getting data from the API 
  - `RetrofitInstance`: initializing the services as an singleton
- `data`: contains the room database DAO and repository
- `model`: dataclasses for the products 
- `viewModels`: contains view models for Fragments
  - `ViewModelProvider`: mainly for dependency injection with repository
- Everything else like Fragments, Activities and Utils are in the main folder 

## Implementation
- In `OfflineProductsRepository`
  - Check if the data is offline
  - If not, fetch the data from the API, else get the data from the local database
    - If the request from the API succeeded, sync with local database  
- Pagination
  - Use button to increment/decrement the page number in the view model, and make request for the
    new page data based on the new current page 