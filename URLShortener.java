import java.util.HashMap;
import java.util.Map;

public class URLShortener {

    private Map<String, String> urlMap;
    private Map<String, String> reverseUrlMap;
    private String domain;
    private int urlId;

    public URLShortener(String domain) {
        this.urlMap = new HashMap<>();
        this.reverseUrlMap = new HashMap<>();
        this.domain = domain;
        this.urlId = 1; // Start with 1 to avoid zero value issues
    }

    // Function to shorten a given URL
    public String shortenURL(String longUrl) {
        if (reverseUrlMap.containsKey(longUrl)) {
            return reverseUrlMap.get(longUrl);
        }
        String shortUrl = domain + "/" + encode(urlId);
        urlMap.put(shortUrl, longUrl);
        reverseUrlMap.put(longUrl, shortUrl);
        urlId++;
        return shortUrl;
    }

    // Function to expand a shortened URL back to the original
    public String expandURL(String shortUrl) {
        if (!urlMap.containsKey(shortUrl)) {
            return "Invalid short URL!";
        }
        return urlMap.get(shortUrl);
    }

    // Simple hash function to encode URLs
    private String encode(int id) {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder shortUrl = new StringBuilder();
        while (id > 0) {
            shortUrl.append(characters.charAt(id % characters.length()));
            id = id / characters.length();
        }
        return shortUrl.reverse().toString(); // Reverse the string to ensure the order is correct
    }

    // Main method to demonstrate the functionality
    public static void main(String[] args) {
        URLShortener urlShortener = new URLShortener("http://short.url");

        // Shortening a URL
        String shortUrl = urlShortener.shortenURL("https://www.example.com");
        System.out.println("Short URL: " + shortUrl);

        // Expanding the URL
        String longUrl = urlShortener.expandURL(shortUrl);
        System.out.println("Long URL: " + longUrl);

        // Trying to expand an invalid short URL
        String invalidUrl = urlShortener.expandURL("http://short.url/invalid");
        System.out.println("Invalid URL Expansion: " + invalidUrl);
    }
}
