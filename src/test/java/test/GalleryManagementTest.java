package test;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hexaware.dao.VirtualArtGalleryImpl;
import com.hexaware.entity.Gallery;
import com.hexaware.util.DBConnection;

/**
 * Unit tests for Gallery management functionality.
 */
public class GalleryManagementTest {
    private static Connection connection;
    private static VirtualArtGalleryImpl service;

    /**
     * Establishes a connection to the database and initializes the service before each test.
     */
    @Before
    public void setUp() {
        connection = DBConnection.getConnection();
        service = new VirtualArtGalleryImpl();
    }

    /**
     * Test case to verify adding a new gallery.
     */
    @Test
    public void testAddingNewGallery() {
        Gallery gallery = new Gallery(4, "name4", "desc4", "loc4", 1, "10 Am - 12 PM");
        ResultSet rs = null;
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO gallery VALUES(?,?,?,?,?,?)");
            ps.setInt(1, gallery.getGalleryId());
            ps.setString(2, gallery.getName());
            ps.setString(3, gallery.getDescription());
            ps.setString(4, gallery.getLocation());
            ps.setInt(5, gallery.getCurator());
            ps.setString(6, gallery.getOpeningHours());
            ps.execute();
            rs = ps.executeQuery("SELECT * FROM gallery WHERE galleryID=4");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(true, isEqual(gallery, rs));
    }

    /**
     * Test case to verify updating an existing gallery.
     */
    @Test
    public void testUpdatingNewGallery() {
        Gallery gallery = new Gallery(2, "name2", "desc4", "loc4", 1, "11 Am - 12 PM");
        ResultSet rs = null;
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE gallery SET Name=?, Description=?, Location=?, Curator=?, OpeningHours=? WHERE galleryId=?");
            ps.setString(1, gallery.getName());
            ps.setString(2, gallery.getDescription());
            ps.setString(3, gallery.getLocation());
            ps.setInt(4, gallery.getCurator());
            ps.setString(5, gallery.getOpeningHours());
            ps.setInt(6, gallery.getGalleryId());
            ps.execute();
            rs = ps.executeQuery("SELECT * FROM gallery WHERE galleryID=2");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(true, isEqual(gallery, rs));
    }

    /**
     * Test case to verify removing a gallery.
     */
    @Test
    public void testRemoveGallery() {
        int rowsAffected = 0;
        Statement s;
        try {
            s = connection.createStatement();
            rowsAffected = s.executeUpdate("DELETE FROM gallery WHERE galleryID=4");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(1, rowsAffected);
    }

    /**
     * Closes the connection after each test.
     */
    @After
    public void tearDown() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper method to check if the retrieved gallery matches the expected gallery.
     * @param gallery The expected gallery object.
     * @param rs The ResultSet containing the retrieved gallery data from the database.
     * @return True if the retrieved gallery matches the expected gallery, false otherwise.
     */
    public boolean isEqual(Gallery gallery, ResultSet rs) {
        try {
            if (rs.next()) {
                return gallery.getGalleryId() == rs.getInt(1) && gallery.getCurator() == rs.getInt(5) &&
                        gallery.getName().equals(rs.getString(2)) && gallery.getDescription().equals(rs.getString(3)) &&
                        gallery.getLocation().equals(rs.getString(4)) && gallery.getOpeningHours().equals(rs.getString(6));
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return false;
    }
}
