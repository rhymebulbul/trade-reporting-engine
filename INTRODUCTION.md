### Trade Reporting Engine

#### Introduction

The Trading Engine is a Spring Boot application designed to process XML event files, extract specific elements, store them in a PostgreSQL database, filter the events based on predefined criteria, and report the filtered events in JSON format via a REST API. This application leverages the power of Spring Boot for rapid development, JPA for database interactions, and standard Java XML parsing libraries for reading and processing XML files.

#### Design and Implementation

**1. XML Parsing and Data Extraction:**
The application uses `javax.xml.parsers.DocumentBuilder` and `javax.xml.xpath.XPath` to parse XML files and extract the required elements. The extracted elements include `buyer_party`, `seller_party`, `premium_amount`, and `premium_currency`. These elements are mapped to corresponding database columns.

**2. Database Interaction:**
The extracted data is stored in a PostgreSQL database using Spring Data JPA. The database schema is defined to store the event details, and JPA entities are created to map the XML elements to database columns.

**3. Filtering Criteria:**
The application implements filtering logic based on the following criteria:
- The `seller_party` is either `EMU_BANK` with `premium_currency` as `AUD` or `BISON_BANK` with `premium_currency` as `USD`.
- The `seller_party` and `buyer_party` must not be anagrams.

The filtering logic is designed to be easily extendable, allowing for additional criteria to be added without impacting existing filters.

**4. REST API:**
A REST API endpoint is provided to trigger the processing and reporting of events. The endpoint is versioned (`/api/v1/events/filtered`) to support future enhancements and backward compatibility. The API returns the filtered events in JSON format.

**5. Swagger Documentation:**
Swagger is integrated into the application to provide interactive API documentation. This allows users to explore and test the API endpoints easily.

**6. Configuration and Profiles:**
The application uses Spring profiles to manage different configurations for various environments (e.g., local, development, production). Environment-specific configurations are defined in profile-specific properties files.

**7. Build and Dependency Management:**
The project uses Gradle for build automation and dependency management. Key dependencies include Spring Boot, Spring Data JPA, PostgreSQL driver, and Springdoc OpenAPI for Swagger integration.

**8. Testing and Code Coverage:**
JUnit and JaCoCo are used for unit testing and code coverage analysis, with 90% line coverage. The application includes tests for XML parsing, database interactions, and filtering logic to ensure robustness and reliability.

**9. Security and Dependency Management:**
OWASP Dependency-Check is integrated to identify and manage security vulnerabilities in project dependencies.

#### Assumptions and Trade-offs

**Assumptions:**
- The XML files are well-formed and follow a consistent structure.
- The database schema is predefined and matches the extracted XML elements.
- The filtering criteria are fixed but can be extended in the future.

**Trade-offs:**
- Using `javax.xml.parsers.DocumentBuilder` and `javax.xml.xpath.XPath` for XML parsing is simple and effective but may not be the most performant solution for very large XML files.
- Storing the extracted data in a relational database (PostgreSQL) provides strong consistency and query capabilities but may introduce latency compared to in-memory data processing.
- The filtering logic is implemented in the application layer, which provides flexibility but may impact performance if the dataset is large.

#### Conclusion

The Trading Engine is a scalable and maintainable solution for processing and reporting trade events. It leverages Spring Boot's capabilities for rapid development, JPA for seamless database interactions, and standard Java libraries for XML processing. The design ensures that the application can be easily extended to accommodate future requirements and changes in business logic.