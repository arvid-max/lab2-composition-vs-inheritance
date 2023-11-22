package cars;

public enum Direction {
    NORTH {
        public Direction rotateClockwise() {return EAST;};
        public Direction rotateCounterClockwise() {return WEST;};
    },
    EAST {
        public Direction rotateClockwise() {return SOUTH;};
        public Direction rotateCounterClockwise() {return NORTH;};
    },
    SOUTH {
        public Direction rotateClockwise() {return WEST;};
        public Direction rotateCounterClockwise() {return EAST;};
    },
    WEST {
        public Direction rotateClockwise() {return NORTH;};
        public Direction rotateCounterClockwise() {return SOUTH;};
    };

    public abstract Direction rotateClockwise();
    public abstract Direction rotateCounterClockwise();

}
