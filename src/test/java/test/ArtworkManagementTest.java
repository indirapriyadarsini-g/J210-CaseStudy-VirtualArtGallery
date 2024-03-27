package test;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hexaware.dao.VirtualArtGalleryImpl;
import com.hexaware.entity.Artwork;
import com.hexaware.util.DBConnection;

/**
 * Unit tests for Artwork management functionality.
 */
public class ArtworkManagementTest {
    private static Connection connection;
    private static VirtualArtGalleryImpl service;

    /**
     * Establishes a connection to the database and initializes the service before each test.
     */
    @BeforeClass
    public static void setUp() {
        connection = DBConnection.getConnection();
        service = new VirtualArtGalleryImpl();
    }

    /**
     * Test case to verify if artwork is added successfully.
     */
    @Test
    public void testArtworkAdded() {

        Artwork artwork = new Artwork(9, "artwork9", "desc9", LocalDate.parse("2024-03-27"), "medium9", "https://art9.com", 1);         
            assertEquals(true,service.addArtwork(artwork));

    }

    /**
     * Test case to verify if artwork is updated successfully.
     */
    @Test
    public void testUpdateArtwork() {

        Artwork artwork = new Artwork(2, "artwork2", "desc2", LocalDate.parse("2024-07-31"), "medium2", "https://art2.com", 1);
        assertEquals(true,service.updateArtwork(artwork));
    }

    /**
     * Test case to verify if artwork is removed successfully.
     */
    @Test
    public void testRemoveArtwork() {
        int artworkId = 9; 
        assertEquals(true,service.removeArtwork(artworkId));
    }

    /**
     * Test case to verify searching for an artwork by its ID.
     */
    @Test
    public void testSearchArtwork() {
//        Statement st;
//        ResultSet rs = null;
        
        int artworkId = 2;
        Artwork artwork = service.getArtworkById(artworkId);
        assertEquals(artworkId,artwork.getArtworkId());
    }
        
        
//        try {
//            artwork = service.getArtworkById(artworkId);
//            st = connection.createStatement();
//            rs = st.executeQuery("SELECT * FROM Artwork WHERE ArtworkID=2");
//            assertEquals(true, isEqual(artwork,rs));
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }

    /**
     * Helper method to check if the retrieved artwork matches the expected artwork.
     * @param artwork The expected artwork object.
     * @param rs The ResultSet containing the retrieved artwork data from the database.
     * @return True if the retrieved artwork matches the expected artwork, false otherwise.
     * @throws SQLException If an SQL error occurs.
     */
    private boolean isEqual(Artwork artwork, ResultSet rs) throws SQLException {
        if (rs.next()) {
            return artwork.getArtistId() == rs.getInt(1) && 
            		artwork.getArtworkId() == rs.getInt(7) &&
                    artwork.getTitle().equals(rs.getString(2)) && 
                    artwork.getDescription().equals(rs.getString(3)) &&
                    artwork.getCreationDate().equals(LocalDate.parse(rs.getString(4))) && 
                    artwork.getMedium().equals(rs.getString(5)) &&
                    artwork.getImageUrl().equals(rs.getString(6));
        }
        return false;
    }
}
