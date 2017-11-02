package com.chess.engine.board;
import com.chess.engine.pieces.Piece;
import java.util.HashMap;
import java.util.Map;

/*
 * Created by Kevin Woo
 * Reference: Amir Afghani
 */

// Create an abstract single tile and will number tiles from 1 to 64

public abstract class Tile {
    // once instantiated, can't modify the value
    protected final int tileCoordinate;
    private static final Map<Integer, EmptyTile> EMPTY_TILES = createAllPossibleEmptyTiles();

    Tile (int tileCoordinate){
        this.tileCoordinate = tileCoordinate;
    }

    public abstract boolean isTileOccupied();

    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles(){
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

        for (int i=0; i<64; i++){
            emptyTileMap.put(i, new EmptyTile(i));
        }

        return ImmutableMap.copyOf(emptyTileMap);   // part of guavo library
    }
    public abstract Piece getPiece();

    public static final class EmptyTile extends Tile{
        EmptyTile(final int tileCoordinate){
            super(tileCoordinate);
        }

        @Override
        public boolean isTileOccupied(){
            return false;
        }

        @Override
        public Piece getPiece(){
            return null;
        }
    }

    public static final class OccupiedTile extends Tile{

        private final Piece pieceOnTile;

        OccupiedTile(int tileCoordinate, Piece pieceOnTile){
            super(tileCoordinate);
            this.pieceOnTile = pieceOnTile;
        }

        @Override
        public boolean isTileOccupied(){
            return true;
        }

        @Override
        public Piece getPiece(){
            return pieceOnTile;
        }
    }

}
