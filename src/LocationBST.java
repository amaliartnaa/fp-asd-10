public class LocationBST {
    private class Node {
        City city;
        Node left, right;

        Node(City city) {
            this.city = city;
        }
    }

    private Node root;

    public void addCity(City city) {
        root = addCityRecursive(root, city);
    }

    private Node addCityRecursive(Node current, City city) {
        if (current == null) {
            return new Node(city);
        }
        if (city.getCity().compareTo(current.city.getCity()) < 0) {
            current.left = addCityRecursive(current.left, city);
        } else if (city.getCity().compareTo(current.city.getCity()) > 0) {
            current.right = addCityRecursive(current.right, city);
        }
        return current;
    }

    public City findCity(String cityName) {
        return findCityRecursive(root, cityName);
    }

    private City findCityRecursive(Node current, String cityName) {
        if (current == null) {
            return null;
        }
        if (cityName.equals(current.city.getCity())) {
            return current.city;
        }
        return cityName.compareTo(current.city.getCity()) < 0
                ? findCityRecursive(current.left, cityName)
                : findCityRecursive(current.right, cityName);
    }
}
