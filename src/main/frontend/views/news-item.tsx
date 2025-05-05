import { useState, useEffect, useRef, useCallback } from "react";

interface NewsItem {
    id: number;
    title: string;
    link: string;
    description: string;
    pubDate: string; // keep as string for JSON compatibility
    imageUrl: string;
    creator: string;
}

export default function NewsList() {
    const [newsItems, setNewsItems] = useState<NewsItem[]>([]);
    const [page, setPage] = useState(1);
    const [loading, setLoading] = useState<boolean>(false);
    const [error, setError] = useState<string | null>(null);
    const [hasMore, setHasMore] = useState<boolean>(true);

    const observer = useRef<IntersectionObserver | null>(null);
    const lastArticleRef = useCallback(
        (node: HTMLDivElement | null) => {
            if (loading) return;
            if (observer.current) observer.current.disconnect();
            observer.current = new IntersectionObserver(entries => {
                if (entries[0].isIntersecting && hasMore) {
                    setPage(prevPage => prevPage + 1);
                }
            });
            if (node) observer.current.observe(node);
        },
        [loading, hasMore]
    );

    useEffect(() => {
        const fetchNewsItems = async () => {
            setLoading(true);
            setError(null);
            try {
                const response = await fetch(`http://localhost:8080/news?page=${page}`);
                if (!response.ok) throw new Error("There was a problem fetching the news. Please contact a system administrator.");
                const newItems: NewsItem[] = await response.json();

                setNewsItems(prevItems => [...prevItems, ...newItems]);
                setHasMore(newItems.length > 0); // If response is empty, no more pages
            } catch (err: any) {
                setError(err.message);
            } finally {
                setLoading(false);
            }
        };

        fetchNewsItems();
    }, [page]);

    return (
        <main className="articles">
            {newsItems.map((item, index) => {
                const isLast = index === newsItems.length - 1;
                return (
                    <article
                        className="article-item"
                        key={item.id}
                        ref={isLast ? lastArticleRef : null}
                    >
                        <div className="article-text">
                            <p className="date">{new Date(item.pubDate).toLocaleDateString("en-US", {
                                month: "short",
                                day: "2-digit",
                                year: "numeric"
                            })}</p>
                            <a href={item.link} target="_blank" rel="noopener noreferrer">
                                <h2>{item.title}</h2>
                                <p className="summary">{item.description}</p>
                            </a>
                            <p className="creator">{item.creator}</p>
                        </div>
                        <a href={item.link} target="_blank" rel="noopener noreferrer">
                            <img src={item.imageUrl} alt="article" className="article-img" />
                        </a>
                    </article>
                );
            })}
            {loading && <div>Loading more...</div>}
            {error && <div className="error">{error}</div>}
            {!hasMore && <div>No more articles</div>}
        </main>
    );
}
