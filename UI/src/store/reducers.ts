import { NodesListState, NodeActionType, Node } from './types'
import { CREATE, DELETE } from './actionTypes'

const initialState: NodesListState = {
   nodesList: [] 
}

const createNode = (state: NodesListState, node: Node): NodesListState => {
   return {
      ...state,
      nodesList: state.nodesList.concat(node)
   }
}

const deleteNode = (state: NodesListState, id: string): NodesListState => {
   return {
      ...state,
      nodesList: state.nodesList.filter(v => v.id !== id)
   }
}

export const reducer = 
   (state: NodesListState = initialState, action: NodeActionType): NodesListState => {
      switch (action.type) {
         case CREATE: return createNode(state, action.payload)
         case DELETE: return deleteNode(state, action.id)
         default: return state
      }
      
}