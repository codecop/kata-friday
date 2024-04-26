public class Position
{
    public int X { get; }
    public int Y { get; }
    
    public Position()
    {
        X = 0;
        Y = 0;
    }

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