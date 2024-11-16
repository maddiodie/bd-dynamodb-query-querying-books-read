import com.amazon.ata.aws.dynamodb.DynamoDbClientProvider;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedList;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;

import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    private DynamoDBMapper mapper;

    /**
     * Allows access to and manipulation of Book objects from the data store.
     * @param mapper Access to DynamoDB
     */
    public BookDAO(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Uses the query() method to retrieve all the items from the BooksRead table that have a given
     * employee id value.
     * @param employeeId the employee ID
     * @return the PaginatedQueryList that is returned from the query
     */
    public List<Book> getBooksReadByEmployee(String employeeId) {
        Book book = new Book();
        book.setId(employeeId);

        DynamoDBQueryExpression<Book> queryExpression = new DynamoDBQueryExpression<Book>()
                .withHashKeyValues(book);
        mapper = new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient());

        PaginatedQueryList<Book> pagList = mapper.query(Book.class, queryExpression);
        List<Book> bookList = new ArrayList<>(pagList);

        return bookList;
    }

}
