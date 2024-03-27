package com.hexaware.dao;

import java.sql.SQLException;
import java.util.List;

import com.hexaware.entity.Artwork;
import com.hexaware.myexceptions.ArtWorkNotFoundException;
import com.hexaware.myexceptions.UserNotFoundException;

/**
 * Interface for virtual art gallery operations.
 */
public interface IVirtualArtGallery {
    // Artwork Management
    
    /**
     * Adds an artwork to the virtual art gallery.
     * @param artwork the artwork to add
     * @return true if the artwork is added successfully, false otherwise
     */
    boolean addArtwork(Artwork artwork);
    
    /**
     * Updates an artwork in the virtual art gallery.
     * @param artwork the updated artwork
     * @return true if the artwork is updated successfully, false otherwise
     */
    boolean updateArtwork(Artwork artwork);
    
    /**
     * Removes an artwork from the virtual art gallery.
     * @param artworkId the ID of the artwork to remove
     * @return true if the artwork is removed successfully, false otherwise
     */
    boolean removeArtwork(int artworkId);
    
    /**
     * Retrieves an artwork by its ID.
     * @param artworkId the ID of the artwork to retrieve
     * @return the artwork object
     */
    Artwork getArtworkById(int artworkId);
    
    /**
     * Searches for artworks in the virtual art gallery based on a keyword.
     * @param keyword the keyword to search for
     * @return a list of artworks matching the keyword
     * @throws SQLException if a database access error occurs
     * @throws ArtWorkNotFoundException if no artworks are found
     */
    List<Artwork> searchArtworks(String keyword) throws SQLException, ArtWorkNotFoundException;
    
    // User favorites
    
    /**
     * Adds an artwork to the favorites of a user.
     * @param userId the ID of the user
     * @param artworkId the ID of the artwork to add to favorites
     * @return true if the artwork is added to favorites successfully, false otherwise
     * @throws SQLException if a database access error occurs
     * @throws UserNotFoundException if the user is not found
     */
    boolean addArtworkToFavorite(int userId, int artworkId) throws SQLException, UserNotFoundException;
    
    /**
     * Removes an artwork from the favorites of a user.
     * @param userId the ID of the user
     * @param artworkId the ID of the artwork to remove from favorites
     * @return true if the artwork is removed from favorites successfully, false otherwise
     * @throws SQLException if a database access error occurs
     * @throws UserNotFoundException if the user is not found
     */
    boolean removeArtworkFromFavorite(int userId, int artworkId) throws SQLException, UserNotFoundException;
    
    /**
     * Retrieves the favorite artworks of a user.
     * @param userId the ID of the user
     * @return true if the favorite artworks are retrieved successfully, false otherwise
     * @throws SQLException if a database access error occurs
     * @throws UserNotFoundException if the user is not found
     */
    boolean getUserFavoriteArtworks(int userId) throws SQLException, UserNotFoundException;
}
