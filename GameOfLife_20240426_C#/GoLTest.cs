using NUnit.Framework;
using System.Collections.Generic;

[TestFixture]
public class GolTest
{
    private Grid grid;
    private Rules rules;
    
    [SetUp]
    public void InitializeGrid() {
        grid = new Grid();
        rules = new Rules();
    }
    
    [Test]
    public void new_grid_has_only_empty_space()
    {
        AssertCell<EmptySpace>(grid);
    }
    
    [Test]
    public void new_grid_with_seed_has_one_alive_cell()
    {
        grid.BringAlive(new Position());

        AssertCell<AliveCell>(grid);
    }
    
    // would add a test for another position now...

    [Test]
    public void living_cell_with_no_neighbours_dies()
    {
        grid.BringAlive(new Position());

        var newGrid = rules.Evolve(grid);
        
        AssertCell<EmptySpace>(newGrid);
    }
    
    [Test]
    public void living_cell_with_three_neighbours_stays_alive()
    {
        grid.BringAlive(new Position());
        grid.BringAlive(new Position(1,0));
        grid.BringAlive(new Position(1,1));
        grid.BringAlive(new Position(0,1));

        var newGrid = rules.Evolve(grid);
        
        AssertCell<AliveCell>(newGrid);
    }
    
    // only rule that uses current cell state is living/2 -> alive
    
    [Test]
    public void living_cell_with_two_neighbours_stays_alive()
    {
        grid.BringAlive(new Position());
        grid.BringAlive(new Position(1,0));
        grid.BringAlive(new Position(1,1));

        var newGrid = rules.Evolve(grid);
        
        AssertCell<AliveCell>(newGrid);
    }
    
    // [Test] - WIP
    public void dead_cell_with_two_neighbours_stays_dead()
    {
        grid.BringAlive(new Position(1,0));
        grid.BringAlive(new Position(1,1));

        var newGrid = rules.Evolve(grid);
        
        AssertCell<EmptySpace>(newGrid);
    }
    
    public void AssertCell<T>(Grid grid) 
    {
        var cell = grid.GetCell(new Position());
        Assert.That(cell, Is.InstanceOf<T>());
    }
    
}

/**
 * Contain rules but does not know anything about Grid.
 */
public class Rules {
 
    public Grid Evolve(Grid grid) {
        var newGrid = new Grid();
        NeighbourCount count = grid.CountNeighboursOf(new Position(0,0));
        var cell = grid.GetCell(new Position(0,0));
        // TODO NEXT pass cell state to Rules?
        // or pass newGrid to cell state polymorphic?
        count.ApplyRules(newGrid, new Position(0,0));
        return newGrid;
    }
    
}

/**
  Design sketch 1: using equality and the code in rules has if as usual
     e.g. if (count == NeighbourCount.Three()) {

  Design sketch 2: using a lookup with the wrapped values as keys and
     the lookup uses equality to match keys
     e.g. 
        Rules = [
            AliveOrDead, NeighbourCount => AliveOrDead
        ]
        World.evolve(rules, grid) {
            foreach grid.cell { applyRules }

            applyRules(cell, rules) {
                foreach rule in rules {
                    if(rule.matches(cell)) setMatch
                }
            }
        }

  Design sketch 3: use polymorphy to distribute rules
  
  Design sketch 4: expose relevant queries as domain queries, 
    e.g. count.isTwoOrThree() <- DSL, but not OCP, and primitive
    e.g. count.willLiveOn() <- Rule logic naming, but Shotgun Surgery
     
*/

/**
 * Manages Cells. Does not know Rules.
 */
public class Grid {
    private List<Position> cells = new List<Position>();

    public void BringAlive(Position position) {
        this.cells.Add(position);
    }
    
    public GridSpace GetCell(Position position) {
        if (this.cells.Contains(position)) {
            return new AliveCell();
        }
        return new EmptySpace();
    }
    
    public NeighbourCount CountNeighboursOf(Position position) {
        int countNeighbours = 0;
        // TODO refactor to use list of relative positions etc.
        if (this.cells.Contains(new Position(0,1))) {
            countNeighbours++;
        }
        if (this.cells.Contains(new Position(1,0))) {
            countNeighbours++;
        }
        if (this.cells.Contains(new Position(1,1))) {
            countNeighbours++;
        }
        return new NeighbourCount(countNeighbours);
    }
}

public class NeighbourCount {
    public readonly int count;
    
    public NeighbourCount(int count) {
        this.count = count;
    }
    
    public void ApplyRules(Grid grid, Position position) {
        if (this.count == 2 || this.count == 3) {
            grid.BringAlive(position);   
        }
    }
}

public interface GridSpace {}
public class EmptySpace : GridSpace {}
public class AliveCell : GridSpace {}
