import { CREATE, DELETE } from './actionTypes';

// Single list
export interface Node {
    // TODO
}

// Describing reducer state
export interface NodesListState {
    // List of TODO lists
    nodesList: Array<Node>
}

interface CreateNode {
    type: typeof CREATE,
    payload: Node
}

interface DeleteNode {
    type: typeof DELETE,
    id: string
}

export type NodeActionType = CreateNode | DeleteNode;


