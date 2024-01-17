import unittest


class SampleTest(unittest.TestCase):
    def test_infrastructure(self):
        self.assertEqual(1, 1)


def main():
    unittest.main()


if __name__ == '__main__':
    main()
