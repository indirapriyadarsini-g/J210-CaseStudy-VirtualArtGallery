package com.hexaware.controller;

import java.sql.SQLException;

import com.hexaware.myexceptions.ArtWorkNotFoundException;
import com.hexaware.myexceptions.UserNotFoundException;

/**
 * This interface represents the controller for managing virtual art gallery operations.
 */
public interface IVirtualArtGalleryController {
    // Artwork Management

    /**
     * Adds a new artwork to the virtual art gallery.
     */
    public void addArtwork();

    /**
     * Updates an existing artwork in the virtual art gallery.
     */
    public void updateArtwork();

    /**
     * Removes an artwork from the virtual art gallery.
     */
    public void removeArtwork();

    /**
     * Retrieves an artwork by its unique identifier.
     */
    public void getArtworkById();

    /**
     * Searches for artworks in the virtual art gallery.
     * @throws SQLException if a database access error occurs
     * @throws ArtWorkNotFoundException if the artwork is not found
     */
    public void searchArtworks() throws SQLException, ArtWorkNotFoundException;
    
    // User favorites
    
    /**
     * Adds an artwork to the favorites of a user.
     * @throws SQLException if a database access error occurs
     * @throws UserNotFoundException if the user is not found
     */
    public void addArtworkToFavorite() throws SQLException, UserNotFoundException;

    /**
     * Removes an artwork from the favorites of a user.
     */
    public void removeArtworkFromFavorite();

    /**
     * Retrieves the favorite artworks of a user.
     * @throws SQLException if a database access error occurs
     * @throws UserNotFoundException if the user is not found
     */
    public void getUserFavoriteArtworks() throws SQLException, UserNotFoundException;
}


//boolean addArtwork(Artwork artwork);
//boolean updateArtwork(Artwork artwork);
//boolean removeArtwork(int artworkId);
//Artwork getArtworkById(int artworkId);
//List<Artwork> searchArtworks(String keyword);
//
//// User favorites
//boolean addArtworkToFavorite(int userId, int artworkId);
//boolean removeArtworkFromFavorite(int userId, int artworkId);
//boolean getUserFavoriteArtworks(int userId);