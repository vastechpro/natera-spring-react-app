import { createRoute as createRoute_1 } from "@vaadin/hilla-file-router/runtime.js";
import type { AgnosticRoute as AgnosticRoute_1 } from "@vaadin/hilla-file-router/types.js";
import * as Page_1 from "../views/@index.js";
import * as Page_2 from "../views/news-item.js";
const routes: readonly AgnosticRoute_1[] = [
    createRoute_1("", Page_1),
    createRoute_1("news-item", Page_2)
];
export default routes;
