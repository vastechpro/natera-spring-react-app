import NewsList from "./news-item";

export default function HomeView() {
  return (
      <div className="bg">
          <header>
              <div className="top-bar">
                  <span>Mon, 15 Aug 2022</span>
                  <h1>The New York Times</h1>
                  <div className="lang"></div>
              </div>
              <hr/>
          </header>
          <NewsList/>
      </div>
  );
}

