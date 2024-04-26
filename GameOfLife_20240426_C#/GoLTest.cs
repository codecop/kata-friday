using NUnit.Framework;
 
[TestFixture]
public class GolTest
{
    private Grid grid;
    
    [SetUp]
    public void InitializeGrid() {
        grid = new Grid();
    }
    
    [Test]
    public void new_grid_has_only_empty_space()
    {
        var cell = grid.GetCell(0,0);
        Assert.That(cell, Is.InstanceOf<EmptySpace>());
    }
    
    [Test]
    public void new_grid_with_seed_has_one_alive_cell()
    {
        grid.BringAlive(0,0);

        var cell = grid.GetCell(0,0);
        Assert.That(cell, Is.InstanceOf<AliveCell>());
    }

    [Test]
    public void living_cell_with_no_neighbours_dies()
    {
        grid.BringAlive(0,0);

        var newGrid = grid.Evolve();
        var cell = newGrid.GetCell(0,0);
        Assert.That(cell, Is.InstanceOf<EmptySpace>());
    }
    
}

public class Grid {
    int x = -1;
    // TODO refactor to position
    public void BringAlive(int x, int y) {
        this.x = x;
    }
    public Grid Evolve() {
        return new Grid();
    }
    public GridSpace GetCell(int x, int y) {
        if (this.x == x) {
            return new AliveCell();
        }
        return new EmptySpace();
    }
}

public interface GridSpace {}
public class EmptySpace : GridSpace {}
public class AliveCell : GridSpace {}

public class Position
{
    public int X { get; }
    public int Y { get; }

    public Position(int x, int y)
    {
        X = x;
        Y = y;
    }

    public override bool Equals(object obj)
    {
        if (obj is Position other)
        {
            return X == other.X && Y == other.Y;
        }
        return false;
    }

    public override int GetHashCode()
    {
        unchecked // Overflow is fine, just wrap
        {
            int hash = 17;
            hash = hash * 23 + X.GetHashCode();
            hash = hash * 23 + Y.GetHashCode();
            return hash;
        }
    }

    public override string ToString()
    {
        return $"Position(X: {X}, Y: {Y})";
    }
}

