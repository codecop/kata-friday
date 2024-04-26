using NUnit.Framework;
 
[TestFixture]
public class GolTest
{
    [Test]
    public void new_grid_has_no_cells()
    {
        var grid = new Grid();
        var cell = grid.getCell(0,0);
        Assert.IsInstanceOf(EmptyPlace.class, cell);
    }
}