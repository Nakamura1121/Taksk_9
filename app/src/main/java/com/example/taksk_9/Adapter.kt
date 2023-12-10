class ActionAdapter(private val actions: List<Action>) :
    RecyclerView.Adapter<ActionAdapter.ActionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_action, parent, false)
        return ActionViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActionViewHolder, position: Int) {
        val action = actions[position]
        holder.bind(action)
    }

    override fun getItemCount(): Int = actions.size

    class ActionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(action: Action) {

        }
    }
}
